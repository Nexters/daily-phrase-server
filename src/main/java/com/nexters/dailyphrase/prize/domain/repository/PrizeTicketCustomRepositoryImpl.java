package com.nexters.dailyphrase.prize.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.prize.domain.PrizeTicket;
import com.nexters.dailyphrase.prize.domain.QPrizeTicket;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PrizeTicketCustomRepositoryImpl implements PrizeTicketCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PrizeTicket> findByMemberIdAndStatus(
            Long memberId, PrizeTicketStatus status, int size) {
        QPrizeTicket qPrizeTicket = QPrizeTicket.prizeTicket;

        return queryFactory
                .selectFrom(qPrizeTicket)
                .where(qPrizeTicket.memberId.eq(memberId).and(qPrizeTicket.status.eq(status)))
                .limit(size)
                .fetch();
    }
}
