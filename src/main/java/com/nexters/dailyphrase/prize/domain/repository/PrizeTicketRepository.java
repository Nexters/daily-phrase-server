package com.nexters.dailyphrase.prize.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketRepository
        extends JpaRepository<PrizeTicket, Long>, PrizeTicketCustomRepository {
    int countByMemberIdAndCreatedAtBetweenAndSource(
            Long memberId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            PrizeTicketSource source);

    boolean existsByMemberIdAndSource(Long memberId, PrizeTicketSource source);

    @Modifying
    @Query("UPDATE PrizeTicket pt SET pt.status = :status WHERE pt.id IN :ids")
    void updateStatusByIds(@Param("ids") List<Long> ids, @Param("status") PrizeTicketStatus status);
}
