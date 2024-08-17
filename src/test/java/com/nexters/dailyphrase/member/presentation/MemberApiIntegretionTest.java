package com.nexters.dailyphrase.member.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@ActiveProfiles("test")
class MemberApiIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired private MemberApi memberApi;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private LikeRepository likeRepository;
    @Autowired private FavoriteRepository favoriteRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("회원상세조회 기능 동작 테스트입니다.")
    void 회원_상세조회() throws Exception {
        // given
        String name = "테스트";
        String email = "test@email.com";
        Member member = Member.builder().name(name).email(email).build();
        Long memberId = memberRepository.save(member).getId();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members/{id}", memberId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value("true"))
                .andExpect(jsonPath("$.result.name").value(name))
                .andExpect(jsonPath("$.result.email").value(email));
    }

    @Test
    @DisplayName("존재하지 않는 회원으로 상세 조회 요청을하면, 404 응답이 옵니다.")
    void 존재하지_않는_회원_상세_조회() throws Exception {
        // given
        Long memberId = 0L;

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members/{id}", memberId.toString()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.isSuccess").value("false"));
    }

    @Test
    @DisplayName("회원탈퇴 기능 동작 테스트입니다.")
    void 회원_탈퇴() throws Exception {
        // given
        Member member = Member.builder().build();
        Long memberId = memberRepository.save(member).getId();
        Phrase phrase = Phrase.builder().build();
        Long phraseId = phraseRepository.save(phrase).getId();
        Like like = Like.builder().member(member).phrase(phrase).build();
        Favorite favorite = Favorite.builder().member(member).phrase(phrase).build();
        likeRepository.save(like);
        favoriteRepository.save(favorite);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/members/{id}", memberId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value("true"));
    }

    @Test
    @DisplayName("존재하지 않는 회원으로 탈퇴요청을하면, 404 응답이 옵니다.")
    void 존재하지_않는_회원_탈퇴() throws Exception {
        // given
        Long memberId = 0L;

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/members/{id}", memberId.toString()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.isSuccess").value("false"));
    }

    @Test
    @DisplayName("유효하지 않은 토큰으로 로그인요청을 했을 때, 400응답이 옵니다.")
    void 유효하지않은_토큰_요청() throws Exception {
        String jsonRequest = "{\"identityToken\":\"invalidTokenString\"}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/login/kakao")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
