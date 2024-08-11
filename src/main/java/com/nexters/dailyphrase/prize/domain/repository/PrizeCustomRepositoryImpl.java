package com.nexters.dailyphrase.prize.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.prize.domain.*;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PrizeCustomRepositoryImpl implements PrizeCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final MemberUtils memberUtils;

    @Override
    public PrizeEventResponseDTO.PrizeList findPrizeListDTO(Long eventId) {
        QPrize qPrize = QPrize.prize;
        QPrizeEntry qPrizeEntry = QPrizeEntry.prizeEntry;
        QPrizeTicket qPrizeTicket = QPrizeTicket.prizeTicket;
        QPrizeEntryCheck qPrizeEntryCheck = QPrizeEntryCheck.prizeEntryCheck;

        Long memberId = memberUtils.getCurrentMemberId();

        JPQLQuery<Long> totalParticipantCountQuery =
                new JPAQuery<Long>().select(Expressions.constant(414L));

        JPQLQuery<Long> myEntryCountQuery =
                JPAExpressions.select(qPrizeEntry.id.count())
                        .from(qPrizeEntry)
                        .where(
                                qPrizeEntry
                                        .prize
                                        .id
                                        .eq(qPrize.id)
                                        .and(qPrizeEntry.memberId.eq(memberId)));

        List<PrizeEventResponseDTO.PrizeListItem> prizeListItems =
                queryFactory
                        .select(
                                Projections.constructor(
                                        PrizeEventResponseDTO.PrizeListItem.class,
                                        qPrize.id,
                                        qPrize.event.id,
                                        qPrize.name,
                                        qPrize.shortName,
                                        qPrize.manufacturer,
                                        qPrize.welcomeImageUrl,
                                        qPrize.bannerImageUrl,
                                        qPrize.imageUrl,
                                        qPrize.requiredTicketCount,
                                        totalParticipantCountQuery,
                                        myEntryCountQuery,
                                        Projections.constructor(
                                                PrizeEventResponseDTO.PrizeEntryResult.class,
                                                new CaseBuilder()
                                                        .when(qPrizeEntry.status.isNotNull())
                                                        .then(qPrizeEntry.status)
                                                        .otherwise(PrizeEntryStatus.MISSED),
                                                qPrizeEntry.phoneNumber,
                                                JPAExpressions.selectOne()
                                                        .from(qPrizeEntryCheck)
                                                        .where(
                                                                qPrizeEntryCheck
                                                                        .memberId
                                                                        .eq(memberId)
                                                                        .and(
                                                                                qPrizeEntryCheck
                                                                                        .prizeId.eq(
                                                                                        qPrize.id)))
                                                        .exists())))
                        .from(qPrize)
                        .leftJoin(qPrizeEntry)
                        .on(
                                qPrizeEntry
                                        .prize
                                        .id
                                        .eq(qPrize.id)
                                        .and(qPrizeEntry.memberId.eq(memberId))
                                        .and(qPrizeEntry.status.eq(PrizeEntryStatus.WINNING)))
                        .where(qPrize.event.id.eq(eventId))
                        .fetch();

        Long myTicketCount =
                queryFactory
                        .select(qPrizeTicket.memberId.count())
                        .from(qPrizeTicket)
                        .where(
                                qPrizeTicket
                                        .memberId
                                        .eq(memberId)
                                        .and(qPrizeTicket.status.eq(PrizeTicketStatus.AVAILABLE)))
                        .fetchOne();
        if (myTicketCount == null) {
            myTicketCount = 0L;
        }

        return PrizeEventResponseDTO.PrizeList.builder()
                .myTicketCount(myTicketCount)
                .prizeList(prizeListItems)
                .build();
    }
}
