package com.nexters.dailyphrase.prize.implement;

import java.time.LocalDate;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeTicketQueryAdapter {
    private final PrizeTicketRepository prizeTicketRepository;

    public Integer countPrizeTicketByMemberIdAndLocalDate(
            final Long memberId, final LocalDate date) {
        return prizeTicketRepository.countByMemberIdAndDate(memberId, date);
    }
}
