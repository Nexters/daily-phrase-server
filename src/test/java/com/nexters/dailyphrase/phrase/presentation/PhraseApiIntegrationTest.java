package com.nexters.dailyphrase.phrase.presentation;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhraseApiIntegrationTest {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private LikeRepository likeRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("글귀 목록 조회 기능 테스트입니다.")
    void 글귀_목록_조회_테스트() throws Exception {
        for (int i = 0; i < 20; i++) {
            phraseRepository.save(Phrase.builder().build());
        }

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/phrases")
                                .param("page", "1")
                                .param("size", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.page").value(1))
                .andExpect(jsonPath("$.result.size").value(10))
                .andExpect(jsonPath("$.result.hasNext").value("true"))
                .andExpect(jsonPath("$.result.total").value(20))
                .andExpect(jsonPath("$.result.phraseList").isArray());
    }
}
