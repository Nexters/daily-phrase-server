package com.nexters.dailyphrase.prize.business;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.MAX_EVENT_TICKETS_PER_DAY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.consts.DailyPhraseStatic;
import com.nexters.dailyphrase.common.enums.*;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import com.nexters.dailyphrase.common.jwt.dto.AccessTokenInfo;
import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.prize.domain.*;
import com.nexters.dailyphrase.prize.exception.InsufficientTicketsException;
import com.nexters.dailyphrase.prize.implement.*;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventRequestDTO;
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
    private final PrizeEntryQueryAdapter prizeEntryQueryAdapter;
    private final PrizeEntryCommandAdapter prizeEntryCommandAdapter;
    private final PrizeEntryCheckCommandAdapter prizeEntryCheckCommandAdapter;
    private final PrizeTicketPopupCheckQueryAdapter prizeTicketPopupCheckQueryAdapter;
    private final PrizeTicketPopupCheckCommandAdapter prizeTicketPopupCheckCommandAdapter;
    private final JwtTokenService jwtTokenService;
    private final PrizeEventMapper prizeEventMapper;
    private final MemberUtils memberUtils;

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeEventInfo getPrizeEventInfo(final Long eventId) {
        final PrizeEvent prizeEvent = prizeEventQueryAdapter.findById(eventId);
        return prizeEventMapper.toPrizeEventInfo(prizeEvent);
    }

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeList getPrizeList(final Long eventId) {
        return prizeQueryAdapter.findPrizeListDTO(eventId);
    }

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeEntryResult getPrizeEntryResult(final Long prizeId) {
        Long memberId = memberUtils.getCurrentMemberId();

        List<PrizeEntry> winningPrizeEntryList =
                prizeEntryQueryAdapter.findWinningEntryList(
                        memberId, prizeId, PrizeEntryStatus.WINNING);

        return prizeEventMapper.toPrizeEntryResult(memberId, prizeId, winningPrizeEntryList);
    }

    private boolean isValidEvent(final Long eventId) {
        PrizeEvent prizeEvent = prizeEventQueryAdapter.findById(eventId);
        LocalDateTime start = prizeEvent.getStartAt();
        LocalDateTime end = prizeEvent.getEndAt();
        LocalDateTime now = LocalDateTime.now();
        if (start.isBefore(now)
                && end.isAfter(now)
                && prizeEvent.getStatus() == PrizeEventStatus.ACTIVE) return true;
        return false;
    }

    @Transactional
    public void issuePrizeTicket(final KakaolinkCallbackRequestDTO request) {
        final Long eventId = DailyPhraseStatic.CURRENT_ACTIVE_EVENT_ID;
        String accessToken = request.getAccessToken();
        AccessTokenInfo accessTokenInfo = jwtTokenService.parseAccessToken(accessToken);
        Long memberId = accessTokenInfo.getUserId();
        Integer count =
                prizeTicketQueryAdapter.countPrizeTicketByMemberIdAndLocalDate(
                        memberId, LocalDate.now());

        if (!isValidEvent(eventId)) return;
        if (count >= MAX_EVENT_TICKETS_PER_DAY) return;

        PrizeTicket prizeTicket =
                prizeEventMapper.toPrizeTicket(
                        memberId, PrizeTicketStatus.AVAILABLE, eventId, PrizeTicketSource.SHARE);
        prizeTicketCommandAdapter.create(prizeTicket);
    }

    @Transactional
    public PrizeEventResponseDTO.EnterPhoneNumber enterPhoneNumber(
            final Long prizeId, final PrizeEventRequestDTO.EnterPhoneNumber request) {
        Long memberId = memberUtils.getCurrentMemberId();
        List<PrizeEntry> winningPrizeEntryList =
                prizeEntryQueryAdapter.findWinningEntryList(
                        memberId, prizeId, PrizeEntryStatus.WINNING);
        winningPrizeEntryList.forEach(
                prizeEntry -> prizeEntry.setPhoneNumber(request.getPhoneNumber()));
        return prizeEventMapper.toEnterPhoneNumber(memberId, prizeId, request.getPhoneNumber());
    }

    @Transactional
    public PrizeEventResponseDTO.EnterPrizeEvent enterPrize(
            final PrizeEventRequestDTO.EnterPrizeEvent request) {
        Long memberId = memberUtils.getCurrentMemberId();
        Prize prize = prizeQueryAdapter.findById(request.getPrizeId());
        int requiredTicketCount = prize.getRequiredTicketCount();

        List<PrizeTicket> prizeTicketList =
                prizeTicketQueryAdapter.findPrizeTicketByMemberIdAndStatus(
                        memberId, PrizeTicketStatus.AVAILABLE, requiredTicketCount);
        if (prizeTicketList.size() < requiredTicketCount)
            throw InsufficientTicketsException.EXCEPTION;

        prizeTicketList.forEach(prizeTicket -> prizeTicket.setStatus(PrizeTicketStatus.USED));
        PrizeEntry savedPrizeEntry =
                prizeEntryCommandAdapter.add(prizeEventMapper.toPrizeEntry(memberId, prize));

        return prizeEventMapper.toEnterPrizeEvent(savedPrizeEntry);
    }

    @Transactional
    public PrizeEventResponseDTO.CheckPrizeEntryResult checkPrizeEntryResult(
            PrizeEventRequestDTO.CheckPrizeEntryResult request) {
        Long prizeId = request.getPrizeId();
        Long memberId = memberUtils.getCurrentMemberId();

        PrizeEntryCheck prizeEntryCheck = prizeEventMapper.toPrizeEntryCheck(prizeId, memberId);
        return prizeEventMapper.toCheckPrizeEntryResult(
                prizeEntryCheckCommandAdapter.add(prizeEntryCheck));
    }

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.MyInfo myInfo() {
        Long memberId = memberUtils.getCurrentMemberId();
        Boolean showGetTicketPopup = Boolean.FALSE;
        // 회원가입 응모권을 획득한 기록이 있는 경우

        boolean hasSignupTicket =
                prizeTicketQueryAdapter.existsByMemberIdAndSource(
                        memberId, PrizeTicketSource.SIGNUP);
        boolean isFirstCheck =
                !prizeTicketPopupCheckQueryAdapter.existsByMemberIdAndType(
                        memberId, PrizeTicketPopupType.GET_BY_SIGNUP);

        if (hasSignupTicket && isFirstCheck) {
            showGetTicketPopup = Boolean.TRUE;
            prizeTicketPopupCheckCommandAdapter.add(
                    prizeEventMapper.toPrizeTicketPopupCheck(
                            memberId, PrizeTicketPopupType.GET_BY_SIGNUP));
        }

        return prizeEventMapper.toMyInfo(memberId, showGetTicketPopup);
    }
}
