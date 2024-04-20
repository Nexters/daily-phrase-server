package com.nexters.dailyphrase.readhistory.business;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.readhistory.domain.ReadHistory;

@Mapper
public class ReadHistoryMapper {
    public ReadHistory toReadHistory(
            final Long memberId, final Long phraseId, final String userAgent) {
        return ReadHistory.builder()
                .memberId(memberId)
                .phraseId(phraseId)
                .userAgent(userAgent)
                .build();
    }
}
