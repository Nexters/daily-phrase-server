package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.common.enums.PrizeTicketPopupType;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketPopupCheckRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeTicketPopupCheckQueryAdapter {
    private final PrizeTicketPopupCheckRepository prizeTicketPopupCheckRepository;

    public boolean existsByMemberIdAndType(Long memberId, PrizeTicketPopupType type) {
        return prizeTicketPopupCheckRepository.existsByMemberIdAndType(memberId, type);
    }
}
