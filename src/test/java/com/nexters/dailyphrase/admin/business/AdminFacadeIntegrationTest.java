package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class AdminFacadeIntegrationTest {


    @Autowired private AdminFacade adminFacade;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private PhraseImageRepository phraseImageRepository;

    @Test
    @DisplayName("글귀 등록 로직을 테스트합니다.")
    void 글귀_등록() {



        // given
        AdminRequestDTO.AddPhrase request = AdminRequestDTO.AddPhrase.builder()
                .title("Title")
                .content("Content")
                .viewCount(10)
                .imageUrl("Image URL")
                .fileName("File Name")
                .uuid("UUID")
                .build();


        // when
        adminFacade.addPhrase(request);

        // then
        //Phrase findItem = phraseRepository.findById(phraseId);
        //assertThat(findItem).isEqualTo(savedPhrase);


    }

    @Test
    @DisplayName("관리자 글귀 상세조회 로직을 테스트합니다.")
    void 관리자글귀_상세조회() {



    }
}