package com.nexters.dailyphrase.phrase.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.favorite.domain.QFavorite;
import com.nexters.dailyphrase.like.domain.QLike;
import com.nexters.dailyphrase.phrase.domain.QPhrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.QPhraseImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PhraseCustomRepositoryImpl implements PhraseCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final MemberUtils memberUtils;

    @Override
    public PhraseResponseDTO.PhraseList findPhraseListDTO(int page, int size) {
        QPhrase qPhrase = QPhrase.phrase;
        QPhraseImage qPhraseImage = QPhraseImage.phraseImage;
        QLike qLike = QLike.like;
        QFavorite qFavorite = QFavorite.favorite;

        int offset = (page - 1) * size;
        long totalElemCount = queryFactory.select(qPhrase.count()).from(qPhrase).fetchOne();
        long totalPageCount = (totalElemCount + size - 1) / size;
        boolean hasNext = page < totalPageCount;

        Long memberId = memberUtils.getCurrentMemberId();

        // Use Projections.constructor to directly map the results to PhraseListItem
        List<PhraseResponseDTO.PhraseListItem> phraseListItems =
                queryFactory
                        .select(
                                Projections.constructor(
                                        PhraseResponseDTO.PhraseListItem.class,
                                        qPhrase.id,
                                        qPhrase.title,
                                        qPhrase.content,
                                        qPhraseImage.url.coalesce(""),
                                        qPhraseImage.imageRatio.coalesce(""),
                                        qPhrase.viewCount,
                                        qLike.count().intValue(),
                                        JPAExpressions.selectOne()
                                                .from(qLike)
                                                .where(
                                                        qLike.phrase
                                                                .eq(qPhrase)
                                                                .and(qLike.member.id.eq(memberId)))
                                                .exists(),
                                        JPAExpressions.selectOne()
                                                .from(qFavorite)
                                                .where(
                                                        qFavorite
                                                                .phrase
                                                                .eq(qPhrase)
                                                                .and(
                                                                        qFavorite.member.id.eq(
                                                                                memberId)))
                                                .exists(),
                                        qPhrase.createdAt))
                        .from(qPhrase)
                        .leftJoin(qPhrase.phraseImage, qPhraseImage)
                        .leftJoin(qLike)
                        .on(qLike.phrase.eq(qPhrase))
                        .groupBy(qPhrase.id)
                        .orderBy(qPhrase.createdAt.desc())
                        .offset(offset)
                        .limit(size)
                        .fetch();

        return PhraseResponseDTO.PhraseList.builder()
                .page(page)
                .size(size)
                .hasNext(hasNext)
                .total(totalElemCount)
                .phraseList(phraseListItems)
                .build();
    }

    @Override
    public AdminResponseDTO.AdminPhraseList findAdminPhraseListDTO() {
        QPhrase qPhrase = QPhrase.phrase;
        QPhraseImage qPhraseImage = QPhraseImage.phraseImage;
        QLike qLike = QLike.like;

        List<AdminResponseDTO.AdminPhraseListItem> adminPhraseListItems =
                queryFactory
                        .select(
                                Projections.constructor(
                                        AdminResponseDTO.AdminPhraseListItem.class,
                                        qPhrase.id,
                                        qPhrase.title,
                                        qPhrase.content,
                                        qPhraseImage.fileName.coalesce(""),
                                        qPhraseImage.url.coalesce(""),
                                        qPhrase.createdAt,
                                        qPhrase.viewCount,
                                        qLike.count().intValue()))
                        .from(qPhrase)
                        .leftJoin(qPhrase.phraseImage, qPhraseImage)
                        .leftJoin(qLike)
                        .on(qLike.phrase.eq(qPhrase))
                        .groupBy(qPhrase.id)
                        .orderBy(qPhrase.createdAt.desc())
                        .fetch();

        return AdminResponseDTO.AdminPhraseList.builder().phraseList(adminPhraseListItems).build();
    }
}
