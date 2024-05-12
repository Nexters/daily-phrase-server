package com.nexters.dailyphrase.prize.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import com.nexters.dailyphrase.common.jwt.dto.AccessTokenInfo;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.implement.PrizeCommandAdapter;
import com.nexters.dailyphrase.prize.implement.PrizeQueryAdapter;
import com.nexters.dailyphrase.prize.implement.PrizeTicketCommandAdapter;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.nexters.dailyphrase.share.presentation.dto.KakaolinkCallbackRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrizeEventService {
    private final PrizeQueryAdapter prizeQueryAdapter;
    private final PrizeCommandAdapter prizeCommandAdapter;
    private final PrizeTicketCommandAdapter prizeTicketCommandAdapter;
    private final JwtTokenService jwtTokenService;
    private final PrizeEventMapper prizeEventMapper;

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeList getPrizeList(final Long eventId) {
        return prizeQueryAdapter.findPrizeListDTO(eventId);
    }

    @Transactional
    public void issuePrizeTicket(KakaolinkCallbackRequestDTO request) {
        // TODO - Validation) 행사 기간인지 확인 & 하루 10회 넘었는지 확인

        String accessToken = request.getAccessToken();
        AccessTokenInfo accessTokenInfo = jwtTokenService.parseAccessToken(accessToken);
        PrizeTicket prizeTicket =
                prizeEventMapper.toPrizeTicket(
                        accessTokenInfo.getUserId(), PrizeTicketStatus.AVAILABLE);
        prizeTicketCommandAdapter.create(prizeTicket);
    }
}
