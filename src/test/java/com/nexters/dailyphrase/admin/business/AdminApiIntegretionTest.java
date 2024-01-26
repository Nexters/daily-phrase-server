package com.nexters.dailyphrase.admin.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexters.dailyphrase.admin.presentation.AdminApi;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminApiIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    @DisplayName("글귀 등록 로직을 테스트합니다.")
    void 글귀_등록() throws Exception {

        // given
        String testTitle = "test title";
        String testContent = "test content";
        String testFileName = "test filename";
        String testImageRatio = "16:9";

        String jsonRequest = toJsonString(testTitle, testContent, testFileName, testImageRatio);

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/admin/phrases")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isSuccess").value("true"));

    }

    private String toJsonString(String title, String content, String fileName, String imageRatio) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("title", title);
        jsonNode.put("content", content);
        jsonNode.put("fileName", fileName);
        jsonNode.put("imageRatio", imageRatio);
        return objectMapper.writeValueAsString(jsonNode);
    }

    @Test
    @DisplayName("관리자 글귀 상세조회 로직을 테스트합니다.")
    void 관리자글귀_상세조회() {



    }
}