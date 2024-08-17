package com.nexters.dailyphrase.prize.domain.repository;

import java.util.List;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketCustomRepository {
    List<PrizeTicket> findByMemberIdAndStatus(Long memberId, PrizeTicketStatus status, int size);
}
