package com.nexters.dailyphrase.prize.domain.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketRepository extends JpaRepository<PrizeTicket, Long> {
    default int countByMemberIdAndDate(Long memberId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return countByMemberIdAndCreatedAtBetween(memberId, startOfDay, endOfDay);
    }

    int countByMemberIdAndCreatedAtBetween(
            Long memberId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
