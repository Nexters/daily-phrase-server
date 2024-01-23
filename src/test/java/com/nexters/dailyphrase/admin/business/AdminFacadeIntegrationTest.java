package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.AdminApi;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminApiIntegrationTest {


    @Autowired private AdminFacade adminFacade;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private AdminApi adminApi;
    @Autowired private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    @DisplayName("글귀 등록 로직을 테스트합니다.")
    void 글귀_등록() throws Exception {

//        // given
//        String testTitle="test title";
//        String testContent="test content";
//        String testFileName="test filename";
//        String testImageRatio="16:9";
//
//        String jsonRequest =
//                "{\"title\":"
//                        + testTitle
//                        + ",\"content\":"
//                        + testContent
//                        + ",\"fileName\":"
//                        + testFileName
//                        + ",\"imageRatio\":"
//                        + testImageRatio
//                        + "}";
//
//        // when & then
//
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/api/admin/phrases")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.isSuccess").value("true"));
//

    }

    @Test
    @DisplayName("관리자 글귀 상세조회 로직을 테스트합니다.")
    void 관리자글귀_상세조회() {



    }
}