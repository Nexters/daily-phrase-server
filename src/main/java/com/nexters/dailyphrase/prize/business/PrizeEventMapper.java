package com.nexters.dailyphrase.prize.business;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;

@Mapper
public class PrizeEventMapper {
    public PrizeTicket toPrizeTicket(
            Long memberId, PrizeTicketStatus prizeTicketStatus, Long eventId) {
        return PrizeTicket.builder()
                .memberId(memberId)
                .status(prizeTicketStatus)
                .eventId(eventId)
                .build();
    }
}
