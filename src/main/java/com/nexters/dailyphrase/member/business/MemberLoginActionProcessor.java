package com.nexters.dailyphrase.member.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.prize.business.PrizeEventMapper;
import com.nexters.dailyphrase.prize.implement.PrizeTicketCommandAdapter;
import com.nexters.dailyphrase.prize.implement.PrizeTicketQueryAdapter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberLoginActionProcessor {

    private final Logger logger = LogManager.getLogger(MemberLoginActionProcessor.class);
    private final PrizeTicketQueryAdapter prizeTicketQueryAdapter;
    private final PrizeTicketCommandAdapter prizeTicketCommandAdapter;
    private final PrizeEventMapper prizeEventMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processLoginAction(final Long memberId) {
        try {
            // 회원가입 응모권 발급
            if (!prizeTicketQueryAdapter.existsByMemberIdAndSource(
                    memberId, PrizeTicketSource.SIGNUP))
                prizeTicketCommandAdapter.createMultiple(
                        prizeEventMapper.toPrizeTicketList(memberId, PrizeTicketSource.SIGNUP));
        } catch (Exception e) {
            logger.error("MemberService) 회원가입 응모권 발급 중 에러가 발생했습니다.");
        }
    }
}
