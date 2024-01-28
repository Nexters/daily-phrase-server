package com.nexters.dailyphrase.phrase.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.like.domain.QLike;
import com.nexters.dailyphrase.phrase.domain.QPhrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.QPhraseImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PhraseCustomRepositoryImpl implements PhraseCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public PhraseResponseDTO.PhraseList findPhraseListDTO(int page, int size) {
        QPhrase qPhrase = QPhrase.phrase;
        QPhraseImage qPhraseImage = QPhraseImage.phraseImage;
        QLike qLike = QLike.like;

        int offset = (page - 1) * size;
        long totalElemCount = queryFactory.select(qPhrase.count()).from(qPhrase).fetchOne();
        long totalPageCount = (totalElemCount + size - 1) / size;
        boolean hasNext = page < totalPageCount;

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
                                        qLike.count().intValue()))
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
}
