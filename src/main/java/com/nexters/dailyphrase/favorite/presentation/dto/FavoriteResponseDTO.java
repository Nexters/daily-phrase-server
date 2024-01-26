package com.nexters.dailyphrase.favorite.presentation.dto;

import java.time.LocalDateTime;

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
    public static class FavoriteList {
        private String field;
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
