package com.nexters.dailyphrase.phrase.business;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

@Component
public class PhraseMapper {

    public PhraseResponseDTO.PhraseDetail toPhraseDetail(Phrase phrase) {
        String imageUrl =
                Optional.ofNullable(phrase.getPhraseImage())
                        .map(PhraseImage::getUrl)
                        .orElse(""); // 또는 기본값 사용

        return PhraseResponseDTO.PhraseDetail.builder()
                .title(phrase.getTitle())
                .imageUrl(imageUrl)
                .content(phrase.getContent())
                .viewCount(phrase.getViewCount())
                .build();
    }
}