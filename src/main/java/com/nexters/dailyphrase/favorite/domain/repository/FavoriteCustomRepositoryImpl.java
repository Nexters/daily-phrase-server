package com.nexters.dailyphrase.favorite.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.favorite.domain.QFavorite;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.nexters.dailyphrase.like.domain.QLike;
import com.nexters.dailyphrase.phrase.domain.QPhrase;
import com.nexters.dailyphrase.phraseimage.domain.QPhraseImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteCustomRepositoryImpl implements FavoriteCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId) {
        QPhrase qPhrase = QPhrase.phrase;
        QPhraseImage qPhraseImage = QPhraseImage.phraseImage;
        QLike qLike = QLike.like;
        QFavorite qFavorite = QFavorite.favorite;

        List<FavoriteResponseDTO.FavoriteListItem> favoriteListItems =
                queryFactory
                        .select(
                                Projections.constructor(
                                        FavoriteResponseDTO.FavoriteListItem.class,
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
                                                .exists()))
                        .from(qPhrase)
                        .leftJoin(qPhrase.phraseImage, qPhraseImage)
                        .leftJoin(qLike)
                        .on(qLike.phrase.eq(qPhrase))
                        .where(
                                qPhrase.id.in(
                                        JPAExpressions.select(qFavorite.phrase.id)
                                                .from(qFavorite)
                                                .where(qFavorite.member.id.eq(memberId))))
                        .groupBy(qPhrase.id)
                        .orderBy(qPhrase.createdAt.desc())
                        .fetch();

        return FavoriteResponseDTO.FavoriteList.builder()
                .phraseList(favoriteListItems)
                .total(favoriteListItems.size())
                .build();
    }
}
