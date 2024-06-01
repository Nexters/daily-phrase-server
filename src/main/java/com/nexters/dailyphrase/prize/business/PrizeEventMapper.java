package com.nexters.dailyphrase.prize.business;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;

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

    public PrizeEventResponseDTO.PrizeEntryResult toPrizeEntryResult(
            Long memberId, Long prizeId, PrizeEntryStatus status) {
        return PrizeEventResponseDTO.PrizeEntryResult.builder()
                .prizeId(prizeId)
                .memberId(memberId)
                .status(status)
                .messageTitle(status.getMessageTitle())
                .messageDetail(status.getMessageDetail())
                .build();
    }
}
