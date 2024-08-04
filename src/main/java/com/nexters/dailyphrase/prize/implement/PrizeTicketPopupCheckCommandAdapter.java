package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.PrizeTicketPopupCheck;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketPopupCheckRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeTicketPopupCheckCommandAdapter {
    private final PrizeTicketPopupCheckRepository prizeTicketPopupCheckRepository;

    public PrizeTicketPopupCheck add(PrizeTicketPopupCheck prizeTicketPopupCheck) {
        return prizeTicketPopupCheckRepository.save(prizeTicketPopupCheck);
    }
}
