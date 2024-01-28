package com.nexters.dailyphrase.favorite.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FavoriteApiIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PhraseRepository phraseRepository;

    @Autowired
    private FavoriteRepository favoriteRepository; // Change LikeRepository to FavoriteRepository

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("즐겨찾기 목록 조회 테스트입니다.")
    void 내_즐겨찾기_목록_조회_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        Phrase phrase2 = Phrase.builder().build();
        Long phraseId2 = phraseRepository.save(phrase2).getId();
        Favorite favorite = Favorite.builder().member(member).phrase(phrase).build();
        Favorite favorite2 = Favorite.builder().member(member).phrase(phrase2).build();
        favoriteRepository.save(favorite);
        favoriteRepository.save(favorite2);

        // when & then
        mockMvc.perform(
                        get("/api/v1/favorites/members/{memberId}", memberId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.total").value(2));
    }

    @Test
    @DisplayName("즐겨찾기 기능 동작 테스트입니다.")
    void 즐겨찾기_기능_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest = "{\"memberId\":" + memberId + ",\"phraseId\":" + phraseId + "}";

        // when & then
        mockMvc.perform(
                        post("/api/v1/favorites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true));
    }

    @Test
    @DisplayName("동시에 여러번 즐겨찾기 요청을 보내면, 1개의 요청만 성공해야합니다.")
    void 즐겨찾기_기능_동시요청_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest = "{\"memberId\":" + memberId + ",\"phraseId\":" + phraseId + "}";
        int numberOfThreads = 10;

        // when
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<ResultActions>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(
                    executor.submit(
                            () ->
                                    mockMvc.perform(
                                            post("/api/v1/favorites")
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
    @DisplayName("존재하지 않는 글귀로 즐겨찾기 요청했을 때, 404응답이 옵니다.")
    void 존재하지_않는_글귀_즐겨찾기_예외_응답_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        String jsonRequest = "{\"memberId\":" + memberId + ",\"phraseId\":0}";

        // when & then
        mockMvc.perform(
                        post("/api/v1/favorites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("PHRASE_404_1"));
    }

    @Test
    @DisplayName("존재하지 않는 회원으로 즐겨찾기 요청했을 때, 404응답이 옵니다.")
    void 존재하지_않는_회원_즐겨찾기_예외_응답_테스트() throws Exception {
        // given
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        String jsonRequest = "{\"memberId\":0,\"phraseId\":" + phraseId + "}";

        // when & then
        mockMvc.perform(
                        post("/api/v1/favorites")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("MEMBER_404_1"));
    }

    @Test
    @DisplayName("즐겨찾기 취소 동작 테스트입니다.")
    void 즐겨찾기_취소_기능_테스트() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        Favorite favorite = Favorite.builder().member(member).phrase(phrase).build();
        favoriteRepository.save(favorite);

        String requestURI = "/api/v1/favorites/members/" + memberId + "/phrases/" + phraseId;

        // when & then
        mockMvc.perform(delete(requestURI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true));
    }

    @Test
    @DisplayName("존재하지 않는 즐겨찾기 기록을 취소할 때, 404 응답이 옵니다.")
    void 존재하지_않는_즐겨찾기_취소_예외_테스트() throws Exception {
        String requestURI = "/api/v1/favorites/members/0/phrases/0";

        // when & then
        mockMvc.perform(delete(requestURI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("FAVORITE_404_1"))
                .andExpect(jsonPath("$.isSuccess").value(false));
    }
}
