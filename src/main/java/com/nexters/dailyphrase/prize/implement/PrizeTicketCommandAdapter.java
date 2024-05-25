package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeTicketCommandAdapter {
    private final PrizeTicketRepository prizeTicketRepository;

    public void create(PrizeTicket prizeTicket) {
        prizeTicketRepository.save(prizeTicket);
    }
}
