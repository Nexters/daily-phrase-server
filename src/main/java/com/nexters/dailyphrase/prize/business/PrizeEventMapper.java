package com.nexters.dailyphrase.prize.business;

import java.util.List;
import java.util.stream.IntStream;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.common.consts.DailyPhraseStatic;
import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.common.enums.PrizeTicketPopupType;
import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.*;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;

@Mapper
public class PrizeEventMapper {
    public PrizeTicket toPrizeTicket(
            Long memberId,
            PrizeTicketStatus prizeTicketStatus,
            Long eventId,
            PrizeTicketSource source) {
        return PrizeTicket.builder()
                .memberId(memberId)
                .status(prizeTicketStatus)
                .eventId(eventId)
                .source(source)
                .build();
    }

    public PrizeEventResponseDTO.PrizeEntryResult toPrizeEntryResult(
            Long memberId, Long prizeId, List<PrizeEntry> prizeEntryList) {

        PrizeEntryStatus status = PrizeEntryStatus.MISSED;
        String phoneNumber = "";
        if (!prizeEntryList.isEmpty()) {
            status = PrizeEntryStatus.WINNING;
            phoneNumber = prizeEntryList.get(0).getPhoneNumber();
        }

        return PrizeEventResponseDTO.PrizeEntryResult.builder()
                //                .prizeId(prizeId)
                //                .memberId(memberId)
                .status(status)
                //                .messageTitle(status.getMessageTitle())
                //                .messageDetail(status.getMessageDetail())
                .phoneNumber(phoneNumber)
                .build();
    }

    public PrizeEventResponseDTO.EnterPhoneNumber toEnterPhoneNumber(
            Long memberId, Long prizeId, String phoneNumber) {
        return PrizeEventResponseDTO.EnterPhoneNumber.builder()
                .memberId(memberId)
                .prizeId(prizeId)
                .phoneNumber(phoneNumber)
                .build();
    }

    public PrizeEventResponseDTO.PrizeEventInfo toPrizeEventInfo(PrizeEvent prizeEvent) {
        return PrizeEventResponseDTO.PrizeEventInfo.builder()
                .eventId(prizeEvent.getId())
                .name(prizeEvent.getName())
                .eventStartDateTime(prizeEvent.getStartAt())
                .eventEndDateTime(prizeEvent.getEndAt())
                .eventWinnerAnnouncementDateTime(prizeEvent.getWinnerAnnouncementAt())
                //                .status(prizeEvent.getStatus().getDescription())
                .build();
    }

    public PrizeEntry toPrizeEntry(Long memberId, Prize prize) {
        return PrizeEntry.builder().memberId(memberId).prize(prize).build();
    }

    public PrizeEventResponseDTO.EnterPrizeEvent toEnterPrizeEvent(final PrizeEntry prizeEntry) {
        return PrizeEventResponseDTO.EnterPrizeEvent.builder()
                .memberId(prizeEntry.getMemberId())
                .prizeId(prizeEntry.getPrize().getId())
                .status(prizeEntry.getStatus())
                .build();
    }

    public List<PrizeTicket> toPrizeTicketList(Long memberId, PrizeTicketSource prizeTicketSource) {
        Long eventId = DailyPhraseStatic.CURRENT_ACTIVE_EVENT_ID;
        int limit = DailyPhraseStatic.SIGN_UP_TICKET_COUNT;
        return IntStream.range(0, limit)
                .mapToObj(
                        i ->
                                toPrizeTicket(
                                        memberId,
                                        PrizeTicketStatus.AVAILABLE,
                                        eventId,
                                        prizeTicketSource))
                .toList();
    }

    public PrizeEntryCheck toPrizeEntryCheck(Long prizeId, Long memberId) {
        return PrizeEntryCheck.builder().prizeId(prizeId).memberId(memberId).build();
    }

    public PrizeEventResponseDTO.CheckPrizeEntryResult toCheckPrizeEntryResult(
            PrizeEntryCheck prizeEntryCheck) {
        return PrizeEventResponseDTO.CheckPrizeEntryResult.builder()
                .prizeId(prizeEntryCheck.getPrizeId())
                .memberId(prizeEntryCheck.getMemberId())
                .build();
    }

    public PrizeTicketPopupCheck toPrizeTicketPopupCheck(Long memberId, PrizeTicketPopupType type) {
        return PrizeTicketPopupCheck.builder().memberId(memberId).type(type).build();
    }

    public PrizeEventResponseDTO.MyInfo toMyInfo(Long memberId, Boolean showGetTicketPopup) {
        return PrizeEventResponseDTO.MyInfo.builder()
                .memberId(memberId)
                .showGetTicketPopup(showGetTicketPopup)
                .build();
    }
}
