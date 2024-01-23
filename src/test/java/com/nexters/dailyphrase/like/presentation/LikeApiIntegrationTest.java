package com.nexters.dailyphrase.like.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LikeApiIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private LikeRepository likeRepository;

    private MockMvc mockMvc;

    @Autowired private LikeApi likeApi;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("좋아요 기능 동작 테스트입니다.")
    void 좋아요_기능_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest =
                "{\"memberId\":"
                        + memberId.toString()
                        + ",\"phraseId\":"
                        + phraseId.toString()
                        + "}";

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/likes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value("true"));
    }

    @Test
    @DisplayName("동시에 여러번 좋아요 요청을 보내면, 1개의 요청만 성공해야합니다.")
    void 좋아요_기능_동시요청_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest =
                "{\"memberId\":"
                        + memberId.toString()
                        + ",\"phraseId\":"
                        + phraseId.toString()
                        + "}";
        int numberOfThreads = 10;

        // when
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<ResultActions>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(
                    executor.submit(
                            () ->
                                    mockMvc.perform(
                                            MockMvcRequestBuilders.post("/api/v1/likes")
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(jsonRequest))));
        }

        // then
        int successCount = 0;
        for (Future<ResultActions> future : futures) {
            ResultActions resultActions = future.get();
            if (resultActions.andReturn().getResponse().getStatus() == HttpStatus.OK.value()) {
                successCount++;
            }
        }

        executor.shutdown();
        assertEquals(1, successCount, "하나의 요청만 성공해야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 글귀로 좋아요 요청했을 때, 404응답이 옵니다.")
    void 존재하지_않는_글귀_좋아요_예외_응답_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        String jsonRequest = "{\"memberId\":" + memberId.toString() + ",\"phraseId\":0}";

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/likes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("PHRASE_404_1"));
    }

    @Test
    @DisplayName("존재하지 않는 회원으로 좋아요 요청했을 때, 404응답이 옵니다.")
    void 존재하지_않는_회원_좋아요_예외_응답_테스트() throws Exception {
        // given
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest = "{\"memberId\":0,\"phraseId\":" + phraseId.toString() + "}";

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/likes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("MEMBER_404_1"));
    }

    @Test
    @DisplayName("좋아요 취소 동작 테스트입니다.")
    void 좋아요_취소_기능_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        Like like = Like.builder().member(member).phrase(phrase).build();
        likeRepository.save(like);

        String requestURI =
                "/api/v1/likes/members/" + memberId.toString() + "/phrases/" + phraseId.toString();

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(requestURI)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value("true"));
    }

    @Test
    @DisplayName("존재하지 않는 좋아요 기록을 취소할 때, 404 응답이 옵니다.")
    void 존재하지_않는_좋아요_취소_예외_테스트() throws Exception {
        String requestURI = "/api/v1/likes/members/" + 0 + "/phrases/" + 0;

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(requestURI)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("LIKE_404_1"))
                .andExpect(jsonPath("$.isSuccess").value("false"));
    }
}
