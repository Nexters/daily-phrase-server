package com.nexters.dailyphrase.phrase.presentation.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhraseResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhraseDetail {
        private Long phraseId;
        private String title;
        private String imageUrl;
        private String imageRatio;
        private String content;
        private int viewCount;
        private int likeCount;
        @Builder.Default private Boolean isLike = false;
        @Builder.Default private Boolean isFavorite = false;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhraseListItem {
        private Long phraseId;
        private String title;
        private String content;
        private String imageUrl;
        private String imageRatio;
        private int viewCount;
        private int likeCount;
        @Builder.Default private Boolean isLike = false;
        @Builder.Default private Boolean isFavorite = false;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhraseList {
        private int page;
        private int size;
        private boolean hasNext;
        private long total;
        @Builder.Default private List<PhraseListItem> phraseList = new ArrayList<>();
    }
}
