package com.nexters.dailyphrase.phrase.business;

import java.util.Optional;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

@Mapper
public class PhraseMapper {

    public PhraseResponseDTO.PhraseDetail toPhraseDetail(
            Phrase phrase, int likeCount, boolean isLike, boolean isFavorite) {
        String imageUrl =
                Optional.ofNullable(phrase.getPhraseImage())
                        .map(PhraseImage::getUrl)
                        .orElse(""); // 또는 기본값 사용

        return PhraseResponseDTO.PhraseDetail.builder()
                .phraseId(phrase.getId())
                .title(phrase.getTitle())
                .imageUrl(imageUrl)
                .imageRatio(phrase.getPhraseImage().getImageRatio())
                .content(phrase.getContent())
                .viewCount(phrase.getViewCount())
                .likeCount(likeCount)
                .isLike(isLike)
                .isFavorite(isFavorite)
                .createdAt(phrase.getCreatedAt())
                .build();
    }
}
