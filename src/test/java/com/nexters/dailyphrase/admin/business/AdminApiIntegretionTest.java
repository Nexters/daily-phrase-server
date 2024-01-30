package com.nexters.dailyphrase.admin.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminApiIntegrationTest {

    @Autowired private PhraseRepository phraseRepository;
    @Autowired private AdminFacade adminFacade;
    @Autowired private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("글귀 등록 로직을 테스트합니다.")
    Long 글귀_등록() throws Exception {
        // given
        String testTitle = "test title";
        String testContent = "test content";
        String testFileName = "test filename";
        String testImageRatio = "16:9";

        // when & then
        long phraseId = phraseCreation(testTitle, testContent, testFileName, testImageRatio);
        Optional<Phrase> findItem = phraseRepository.findById(phraseId);

        assertEquals(testContent, findItem.get().getContent());
        assertEquals(testTitle, findItem.get().getTitle());
        assertEquals(testFileName, findItem.get().getPhraseImage().getFileName());
        assertEquals(testImageRatio, findItem.get().getPhraseImage().getImageRatio());
        return phraseId;
    }

    @Test
    @DisplayName("관리자 글귀 상세조회 로직을 테스트합니다.")
    void 관리자글귀_상세조회() throws Exception {
        // given
        String testTitle = "test title2";
        String testContent = "test content2";
        String testFileName = "test filename2";
        String testImageRatio = "16:10";

        long phraseId = phraseCreation(testTitle, testContent, testFileName, testImageRatio);

        // when
        AdminResponseDTO.AdminPhraseDetail adminPhraseDetail =
                adminFacade.getAdminPhraseDetail(phraseId);
        Optional<Phrase> findItem = phraseRepository.findById(phraseId);

        // then
        assertEquals(testTitle, adminPhraseDetail.getTitle());
        assertEquals(testContent, adminPhraseDetail.getContent());
        System.out.println("image url은 return하기만 합니다 imageurl=" + adminPhraseDetail.getImageUrl());
    }
    @Test
    @DisplayName("글귀 수정 로직을 테스트합니다.")
    void 글귀_수정() throws Exception {
        // given

        long phraseId = 글귀_등록();

        String testTitle = "modify_title";
        String testContent = "modify_content";
        String testFileName = "modify_fileName";
        String testImageRatio = "modify_ratio";

        String jsonRequest = toJsonString(testTitle, testContent, testFileName, testImageRatio);

        // when & then
        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/api/admin/phrases/{id}", phraseId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.isSuccess").value("true"))
                        .andExpect(jsonPath("$.result.title").value(testTitle))
                        .andExpect(jsonPath("$.result.content").value(testContent))
                        .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(responseBody);

        String imageUrl = jsonObject.getJSONObject("result").getString("imageUrl");
        String updatedAt = jsonObject.getJSONObject("result").getString("updatedAt");
        String createdAt = jsonObject.getJSONObject("result").getString("createdAt");

        System.out.println("image url은 return하기만 합니다 imageurl=" + imageUrl);
        System.out.println("updatedAt은 return하기만 합니다 updatedAt=" + updatedAt);
        System.out.println("createdAt은 return하기만 합니다 createdAt=" + createdAt);
    }

    @DisplayName("테스트용 글귀 등록 공통 로직")
    private long phraseCreation(
            String testTitle, String testContent, String testFileName, String testImageRatio)
            throws Exception {
        String jsonRequest = toJsonString(testTitle, testContent, testFileName, testImageRatio);

        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/admin/phrases")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.isSuccess").value("true"))
                        .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(responseBody);

        return jsonObject.getJSONObject("result").getInt("id");
    }

    @DisplayName("테스트용 Json build")
    private String toJsonString(String title, String content, String fileName, String imageRatio)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("title", title);
        jsonNode.put("content", content);
        jsonNode.put("fileName", fileName);
        jsonNode.put("imageRatio", imageRatio);
        return objectMapper.writeValueAsString(jsonNode);
    }
}
