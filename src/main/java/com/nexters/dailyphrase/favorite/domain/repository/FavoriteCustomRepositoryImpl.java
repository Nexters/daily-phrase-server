package com.nexters.dailyphrase.favorite.domain.repository;

import java.util.List;

import javax.swing.*;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.favorite.domain.QFavorite;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.nexters.dailyphrase.like.domain.QLike;
import com.nexters.dailyphrase.phrase.domain.QPhrase;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteCustomRepositoryImpl implements FavoriteCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId) {
        QPhrase qPhrase = QPhrase.phrase;
        QLike qLike = QLike.like;
        QFavorite qFavorite = QFavorite.favorite;

        List<FavoriteResponseDTO.FavoriteListItem> favoriteListItems =
                queryFactory
                        .select(
                                Projections.constructor(
                                        FavoriteResponseDTO.FavoriteListItem.class,
                                        qFavorite.phrase.id,
                                        qFavorite.phrase.title,
                                        qFavorite.phrase.content,
                                        qFavorite.phrase.phraseImage.url.coalesce(""),
                                        qFavorite.phrase.phraseImage.imageRatio.coalesce(""),
                                        qFavorite.phrase.viewCount,
                                        qLike.count().intValue()))
                        .from(qFavorite)
                        .leftJoin(qFavorite.phrase, qPhrase)
                        .leftJoin(qLike)
                        .on(qLike.phrase.id.eq(qFavorite.phrase.id))
                        .where(qFavorite.member.id.eq(memberId))
                        .groupBy(qFavorite.phrase.id)
                        .orderBy(qFavorite.createdAt.desc())
                        .fetch();

        return FavoriteResponseDTO.FavoriteList.builder()
                .phraseList(favoriteListItems)
                .total(favoriteListItems.size())
                .build();
    }
}
