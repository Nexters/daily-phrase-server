package com.nexters.dailyphrase.prize.domain.repository;

import static com.nexters.dailyphrase.common.enums.PrizeTicketStatus.AVAILABLE;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.prize.domain.QPrize;
import com.nexters.dailyphrase.prize.domain.QPrizeEntry;
import com.nexters.dailyphrase.prize.domain.QPrizeEvent;
import com.nexters.dailyphrase.prize.domain.QPrizeTicket;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.querydsl.core.types.Projections;
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
        QPrizeEvent qPrizeEvent = QPrizeEvent.prizeEvent;
        QPrizeTicket qPrizeTicket = QPrizeTicket.prizeTicket;

        Long memberId = memberUtils.getCurrentMemberId();

        //        JPQLQuery<Long> totalParticipantCountQuery =
        //                JPAExpressions.select(qPrizeEntry.memberId.countDistinct())
        //                        .from(qPrizeEntry)
        //                        .where(qPrizeEntry.prize.id.eq(qPrize.id));

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

        JPQLQuery<Long> myTicketCountQuery =
                JPAExpressions.select(qPrizeTicket.memberId.count())
                        .from(qPrizeTicket)
                        .where(
                                qPrizeTicket
                                        .memberId
                                        .eq(memberId)
                                        .and(qPrizeTicket.status.eq(AVAILABLE)));

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
                                        myTicketCountQuery))
                        .from(qPrize)
                        .where(qPrize.event.id.eq(eventId))
                        .fetch();

        return PrizeEventResponseDTO.PrizeList.builder()
                .total(prizeListItems.size())
                .prizeList(prizeListItems)
                .build();
    }
}
