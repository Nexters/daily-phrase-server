package com.nexters.dailyphrase.prize.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.common.enums.PrizeTicketPopupType;
import com.nexters.dailyphrase.prize.domain.PrizeTicketPopupCheck;

public interface PrizeTicketPopupCheckRepository
        extends JpaRepository<PrizeTicketPopupCheck, Long> {
    boolean existsByMemberIdAndType(Long memberId, PrizeTicketPopupType type);
}
