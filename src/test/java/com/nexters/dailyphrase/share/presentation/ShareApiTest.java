package com.nexters.dailyphrase.share.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.domain.repository.ShareRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShareApiTest {

    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private ShareRepository shareRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("공유하기 유저데이터 저장 테스트입니다.")
    void 공유하기_유저데이터_저장_테스트() throws Exception {
        // given
        Long memberId = 1L;
        Long phraseId = 1L;
        String jsonRequest = "{\"memberId\":" + memberId + ",\"phraseId\":" + phraseId + "}";

        // when
        mockMvc.perform(
                        post("/api/v1/shares")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.memberId").value(memberId))
                .andExpect(jsonPath("$.result.phraseId").value(phraseId));

        // then
        List<Share> shareList = shareRepository.findAllByMemberIdAndPhraseId(memberId, phraseId);
        assertTrue(shareList.size() >= 1, "공유하기 내역이 기록되지 않았습니다.");
    }
}
