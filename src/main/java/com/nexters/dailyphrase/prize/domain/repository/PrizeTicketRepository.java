package com.nexters.dailyphrase.prize.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketRepository
        extends JpaRepository<PrizeTicket, Long>, PrizeTicketCustomRepository {
    int countByMemberIdAndCreatedAtBetween(
            Long memberId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    int countByMemberIdAndStatus(Long memberId, PrizeTicketStatus status);
}
