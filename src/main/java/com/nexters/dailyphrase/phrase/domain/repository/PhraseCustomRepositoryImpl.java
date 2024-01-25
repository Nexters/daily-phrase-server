package com.nexters.dailyphrase.phrase.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexters.dailyphrase.like.domain.QLike;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.QPhrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.QPhraseImage;
import com.querydsl.core.Tuple;
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

        List<Tuple> results =
                queryFactory
                        .select(qPhrase, qPhraseImage, qLike.count())
                        .from(qPhrase)
                        .leftJoin(qPhrase.phraseImage, qPhraseImage)
                        .fetchJoin()
                        .leftJoin(qLike)
                        .on(qLike.phrase.eq(qPhrase))
                        .groupBy(qPhrase.id)
                        .orderBy(qPhrase.createdAt.asc())
                        .offset(offset)
                        .limit(size)
                        .fetch();

        long totalElemCount = queryFactory.select(qPhrase.count()).from(qPhrase).fetchOne();

        long totalPageCount = (totalElemCount + size - 1) / size;

        boolean hasNext = page < totalPageCount;

        List<PhraseResponseDTO.PhraseListItem> phraseListItems =
                results.stream()
                        .map(
                                tuple -> {
                                    Phrase phrase = tuple.get(qPhrase);
                                    PhraseImage phraseImage = tuple.get(qPhraseImage);
                                    Long likeCount = tuple.get(qLike.count());
                                    return new PhraseResponseDTO.PhraseListItem(
                                            phrase.getId(),
                                            phrase.getTitle(),
                                            phrase.getContent(),
                                            phraseImage != null ? phraseImage.getUrl() : "",
                                            phrase.getViewCount(),
                                            likeCount.intValue());
                                })
                        .toList();

        return new PhraseResponseDTO.PhraseList(
                page, size, hasNext, totalElemCount, phraseListItems);
    }
}
