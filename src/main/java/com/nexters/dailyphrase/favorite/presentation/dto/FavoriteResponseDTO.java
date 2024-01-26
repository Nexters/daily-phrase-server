package com.nexters.dailyphrase.favorite.presentation.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoriteResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddFavorite {
        private Long memberId;
        private Long phraseId;

        @Builder.Default
        @JsonProperty("isFavorite")
        private boolean favorite = true;

        private LocalDateTime favoredAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FavoriteListItem {
        private Long phraseId;
        private String title;
        private String content;
        private String imageUrl;
        private int viewCount;
        private int likeCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FavoriteList {
        private long total;
        @Builder.Default private List<FavoriteListItem> phraseList = new ArrayList<>();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RemoveFavorite {
        private Long memberId;
        private Long phraseId;

        @Builder.Default
        @JsonProperty("isFavorite")
        private boolean favorite = false;

        private LocalDateTime canceledAt;
    }
}
