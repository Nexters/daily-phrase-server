package com.nexters.dailyphrase.phrase.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;

@SpringBootTest
@ActiveProfiles("test")
class PhraseFacadeIntegrationTest {

    @Autowired private PhraseFacade phraseFacade;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private PhraseImageRepository phraseImageRepository;

    final String title = "제목입니다.";
    final String content = "내용입니다.";
    final String imageUrl = "https://avatars.githubusercontent.com/u/53550707?v=4";
    final String fileName = "laptop";
    final String uuid = "550e8400-e29b-41d4-a716-446655440000";

    @Test
    @DisplayName("글귀 상세조회 로직을 테스트합니다.")
    void 글귀_상세조회() {
        // given
        Phrase phrase = Phrase.builder().title(title).content(content).build();
        phrase = phraseRepository.save(phrase);
        Long phraseId = phrase.getId();

        PhraseImage phraseImage =
                PhraseImage.builder().url(imageUrl).fileName(fileName).build();
        phraseImage.setPhrase(phrase);
        phraseImageRepository.save(phraseImage);

        // when
        PhraseResponseDTO.PhraseDetail phraseDetail = phraseFacade.getPhraseDetail(phraseId);

        // then
        assertEquals(title, phraseDetail.getTitle());
        assertEquals(content, phraseDetail.getContent());
        assertEquals(1, phraseDetail.getViewCount());
        assertEquals(imageUrl, phraseDetail.getImageUrl());
    }

    @Test
    @DisplayName("100명이 동시에 조회하면 100의 조회수가 늘어나야 합니다.")
    void 글귀_상세조회_동시요청() throws InterruptedException {
        // given
        final int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        Phrase phrase = Phrase.builder().title(title).content(content).build();
        phrase = phraseRepository.save(phrase);
        Long phraseId = phrase.getId();

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(
                    () -> {
                        try {
                            phraseFacade.getPhraseDetail(phraseId);
                        } finally {
                            latch.countDown();
                        }
                    });
        }

        latch.await();
        executorService.shutdown();

        // then
        Phrase updatedPhrase = phraseRepository.findById(phraseId).orElseThrow();
        assertEquals(numberOfThreads, updatedPhrase.getViewCount());
    }
}
