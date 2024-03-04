package com.nexters.dailyphrase.job;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseScheduler {

    private final PhraseRepository phraseRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void publishScheduledPhrases() {
        phraseRepository.updateByIsPublishDate(LocalDate.now());
    }
}
