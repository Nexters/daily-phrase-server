package com.nexters.dailyphrase.phrase.presentation.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhraseResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhraseDetail {
        private String title;
        private String imageUrl;
        private String content;
        private int viewCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhraseList {
        private String field;
    }
}
