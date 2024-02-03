package com.nexters.dailyphrase.admin.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminApiIntegrationTest {

    @Autowired private PhraseRepository phraseRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private AdminFacade adminFacade;
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private PasswordEncoder passwordEncoder;

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

    @Test
    @DisplayName("존재하지 않는 글귀 수정 요청은 404 응답이 옵니다.")
    void 존재하지않는_글귀_수정요청() throws Exception {
        // given
        long phraseId = 1234567890;
        assertFalse(phraseRepository.existsById(phraseId));

        String testTitle = "modify_title";
        String testContent = "modify_content";
        String testFileName = "modify_fileName";
        String testImageRatio = "modify_ratio";

        String jsonRequest = toJsonString(testTitle, testContent, testFileName, testImageRatio);

        // when&then
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/api/admin/phrases/{id}", phraseId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("글귀 삭제 로직을 테스트합니다.")
    void 글귀_삭제() throws Exception {
        // given
        long phraseId = 글귀_등록();

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/phrases/{id}", phraseId))
                .andExpect(status().isNoContent());

        assertFalse(phraseRepository.existsById(phraseId));
    }

    @Test
    @DisplayName("관리자 로그인 기능 테스트를 합니다.")
    String 관리자_로그인() throws Exception {
        // given
        String name = "Admin1";
        String userId = "AdminId";
        String password = "test1234";
        // role 은 default 값이 ADMIN
        Admin admin = Admin.builder().name(name).userId(userId).password(password).build();
        admin = adminRepository.save(admin);

        // when & then
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("name", name);
        jsonNode.put("userId", userId);
        jsonNode.put("password", password);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/admin/login")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest))
                        .andExpect(status().isUnauthorized())
                        .andExpect(jsonPath("$.isSuccess").value("true"))
                        .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(responseBody);

        String loginUserId = jsonObject.getJSONObject("result").getString("userId");
        String accessToken = jsonObject.getJSONObject("result").getString("accessToken");
        String refreshToken = jsonObject.getJSONObject("result").getString("refreshToken");

        return accessToken;
    }

    //
    //    @Test
    //    @DisplayName("관리자 로그인 후 생성된 Access Token으로 /api/admin URL에 접근합니다.")
    //    String 관리자_로그인_토큰() throws Exception {
    //
    //
    //        String accessToken = 관리자_로그인();
    //        HttpHeaders httpHeaders = new HttpHeaders();
    //        httpHeaders.setBearerAuth(accessToken);
    //
    //        글귀_등록();
    //
    //        mockMvc.perform(
    //                        MockMvcRequestBuilders.get("/api/admin/phrases/1")
    //                                .headers(httpHeaders)
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.isSuccess").value("true"));
    //    }
    @Test
    @DisplayName("관리자 로그인 시 비밀번호를 틀리면 401 에러가 나옵니다.")
    void 관리자_로그인_비밀번호_오류() throws Exception {
        // given
        String name = "Admin1";
        String userId = "AdminId";
        String password = "test1234";
        String WrongPassword = "wrong1234";
        // role 은 default 값이 ADMIN
        Admin admin = Admin.builder().name(name).userId(userId).password(password).build();
        admin = adminRepository.save(admin);

        // when & then
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("name", name);
        jsonNode.put("userId", userId);
        jsonNode.put("password", WrongPassword);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/admin/login")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest))
                        .andExpect(status().isUnauthorized())
                        .andExpect(jsonPath("$.isSuccess").value("false"))
                        .andExpect(jsonPath("$.message").value("자격증명이 없습니다. 아이디 또는 비밀번호가 틀렸습니다."))
                        .andExpect(jsonPath("$.code").value("ADMIN_401_1"))
                        .andReturn();
    }

    @Test
    @DisplayName("관리자 사용자 ID를 틀리면 ADMIN_404_1 에러가 나옵니다")
    void 관리자_로그인_ID오류() throws Exception {

        // given
        String name = "Admin1";
        String userId = "AdminId";
        String password = "test1234";
        String WrongUserId = "AdminId1";
        // role 은 default 값이 ADMIN
        Admin admin = Admin.builder().name(name).userId(userId).password(password).build();
        admin = adminRepository.save(admin);

        // when & then
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("name", name);
        jsonNode.put("userId", WrongUserId);
        jsonNode.put("password", password);
        String jsonRequest = objectMapper.writeValueAsString(jsonNode);

        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/admin/login")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.isSuccess").value("false"))
                        .andExpect(jsonPath("$.message").value("해당 관리자를 찾을 수 없습니다."))
                        .andExpect(jsonPath("$.code").value("ADMIN_404_1"))
                        .andReturn();
    }

    @Test
    @DisplayName("존재하지 않는 글귀 삭제 요청은 204 응답이 옵니다.")
    void 존재하지않는_글귀_삭제요청() throws Exception {
        // given
        long phraseId = 1234567890;
        assertFalse(phraseRepository.existsById(phraseId));

        // when&then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/admin/phrases/{id}", phraseId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("관리자 글귀 목록 조회 기능 테스트입니다.")
    void 관리자_글귀_목록_조회_테스트() throws Exception {
        // given
        for (int i = 0; i < 20; i++) {
            글귀_등록();
        }

        // when & then
        mockMvc.perform(

                        MockMvcRequestBuilders.get("/api/admin/phrases")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.phraseList").isArray())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.result.phraseList.length()").value(20))
                .andReturn();

                                MockMvcRequestBuilders.get("/api/admin/phrases")
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(System.out::println)
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.result.phraseList").isArray())
                        .andExpect(jsonPath("$.isSuccess").value(true))
                        .andExpect(jsonPath("$.result.phraseList.length()").value(20))
                        .andReturn();


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
