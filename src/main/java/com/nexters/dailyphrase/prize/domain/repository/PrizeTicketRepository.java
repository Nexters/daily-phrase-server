package com.nexters.dailyphrase.prize.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketRepository
        extends JpaRepository<PrizeTicket, Long>, PrizeTicketCustomRepository {
    int countByMemberIdAndCreatedAtBetweenAndSource(
            Long memberId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            PrizeTicketSource source);

    boolean existsByMemberIdAndSource(Long memberId, PrizeTicketSource source);
}
