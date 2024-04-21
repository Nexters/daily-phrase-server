package com.nexters.dailyphrase.job;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.nexters.dailyphrase.notification.SendNotification;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseScheduler {

    private final PhraseRepository phraseRepository;
    private final SendNotification sendNotification;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void publishScheduledPhrases() throws IOException {
        LocalDate currentDate = LocalDate.now();
        phraseRepository.updateByIsPublishDate(currentDate);

        List<Phrase> todayPhrase = phraseRepository.findPhraseByPublishDate(currentDate);
        if (!todayPhrase.isEmpty()) //오늘 예약된 글이 있을때만 알림 전송
        {
            Phrase NotificationPhrase =todayPhrase.get(0); // 오늘 업로드예정 글 중 1건만 전송
            final String alarmBody =  NotificationPhrase.getTitle();
            final String alarmPhraseId =  NotificationPhrase.getId().toString();
            sendNotification.sendMessageTo(alarmBody, alarmPhraseId);
        }
    }
}
