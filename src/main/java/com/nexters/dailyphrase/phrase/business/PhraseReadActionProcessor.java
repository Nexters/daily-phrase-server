package com.nexters.dailyphrase.phrase.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.phrase.implement.PhraseCommandAdapter;
import com.nexters.dailyphrase.readhistory.business.ReadHistoryMapper;
import com.nexters.dailyphrase.readhistory.domain.ReadHistory;
import com.nexters.dailyphrase.readhistory.implement.ReadHistoryCommandAdapter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseReadActionProcessor {
    private final Logger logger = LogManager.getLogger(PhraseReadActionProcessor.class);

    private final PhraseCommandAdapter phraseCommandAdapter;
    private final ReadHistoryCommandAdapter readHistoryCommandAdapter;
    private final ReadHistoryMapper readHistoryMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processReadAction(final Long id, final Long memberId, final String userAgent) {
        try {
            phraseCommandAdapter.increaseViewCountById(id);
            ReadHistory readHistory = readHistoryMapper.toReadHistory(memberId, id, userAgent);
            readHistoryCommandAdapter.save(readHistory);
        } catch (Exception e) {
            logger.error("PhraseService) 조회수 증가 & 정보 기록 중 에러가 발생했습니다.");
        }
    }
}
