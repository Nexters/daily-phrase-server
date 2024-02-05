package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Component;

@Component
public class PhraseMapper {

    //    public PhraseResponseDTO.PhraseDetail toPhraseDetail(Phrase phrase) {
    //        String imageUrl =
    //                Optional.ofNullable(phrase.getPhraseImage())
    //                        .map(PhraseImage::getUrl)
    //                        .orElse(""); // 또는 기본값 사용
    //
    //        return PhraseResponseDTO.PhraseDetail.builder()
    //                .phraseId(phrase.getId())
    //                .title(phrase.getTitle())
    //                .imageUrl(imageUrl)
    //                .content(phrase.getContent())
    //                .viewCount(phrase.getViewCount())
    //                .build();
    //    }
}
