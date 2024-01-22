package com.nexters.dailyphrase.member.presentation;

import static org.junit.jupiter.api.Assertions.*;
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

@SpringBootTest
@ActiveProfiles("test")
class MemberApiIntegrationTest {

    private MockMvc mockMvc;

    @Autowired private MemberApi memberApi;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberApi).build();
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
