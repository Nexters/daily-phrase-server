package com.nexters.dailyphrase.prize.business;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.MAX_EVENT_TICKETS_PER_DAY;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import com.nexters.dailyphrase.common.jwt.dto.AccessTokenInfo;
import com.nexters.dailyphrase.prize.domain.PrizeEvent;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.implement.*;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.nexters.dailyphrase.share.presentation.dto.KakaolinkCallbackRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrizeEventService {
    private final PrizeQueryAdapter prizeQueryAdapter;
    private final PrizeEventQueryAdapter prizeEventQueryAdapter;
    private final PrizeCommandAdapter prizeCommandAdapter;
    private final PrizeTicketQueryAdapter prizeTicketQueryAdapter;
    private final PrizeTicketCommandAdapter prizeTicketCommandAdapter;
    private final JwtTokenService jwtTokenService;
    private final PrizeEventMapper prizeEventMapper;

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeList getPrizeList(final Long eventId) {
        return prizeQueryAdapter.findPrizeListDTO(eventId);
    }

    private boolean isValidEvent(final Long eventId) {
        PrizeEvent prizeEvent = prizeEventQueryAdapter.findById(eventId);
        LocalDateTime start = prizeEvent.getStartAt();
        LocalDateTime end = prizeEvent.getEndAt();
        LocalDateTime now = LocalDateTime.now();
        if (start.isBefore(now) && end.isAfter(now)) return true;
        return false;
    }

    @Transactional
    public void issuePrizeTicket(KakaolinkCallbackRequestDTO request) {
        Long eventId = request.getEventId();
        String accessToken = request.getAccessToken();
        AccessTokenInfo accessTokenInfo = jwtTokenService.parseAccessToken(accessToken);
        Long memberId = accessTokenInfo.getUserId();
        Integer count =
                prizeTicketQueryAdapter.countPrizeTicketByMemberIdAndLocalDate(
                        memberId, LocalDate.now());

        if (!isValidEvent(eventId)) return;
        if (count >= MAX_EVENT_TICKETS_PER_DAY) return;

        PrizeTicket prizeTicket =
                prizeEventMapper.toPrizeTicket(memberId, PrizeTicketStatus.AVAILABLE, eventId);
        prizeTicketCommandAdapter.create(prizeTicket);
    }
}
