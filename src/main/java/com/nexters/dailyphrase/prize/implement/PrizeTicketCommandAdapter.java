package com.nexters.dailyphrase.prize.implement;

import java.util.List;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
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

    public void createMultiple(List<PrizeTicket> prizeTicketList) {
        prizeTicketRepository.saveAll(prizeTicketList);
    }

    public void updateMultiple(List<PrizeTicket> prizeTicketList, PrizeTicketStatus status) {
        List<Long> ids = prizeTicketList.stream().map(PrizeTicket::getId).toList();
        prizeTicketRepository.updateStatusByIds(ids, status);
    }
}
