package com.nexters.dailyphrase.phrase.business;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

@Component
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
