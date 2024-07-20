package com.nexters.dailyphrase.prize.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.domain.repository.PrizeTicketRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeTicketQueryAdapter {
    private final PrizeTicketRepository prizeTicketRepository;

    public Integer countPrizeTicketByMemberIdAndLocalDate(
            final Long memberId, final LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return prizeTicketRepository.countByMemberIdAndCreatedAtBetween(
                memberId, startOfDay, endOfDay);
    }

    public List<PrizeTicket> findPrizeTicketByMemberIdAndStatus(
            final Long memberId, final PrizeTicketStatus status, final int count) {
        return prizeTicketRepository.findByMemberIdAndStatus(
                memberId, status, count);
    }
}
