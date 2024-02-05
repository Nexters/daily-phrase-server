package com.nexters.dailyphrase.like.presentation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddLike {
        private Long memberId;
        private Long phraseId;

        @Builder.Default
        @JsonProperty("isLike")
        private boolean like = true;

        private int likeCount;
        private LocalDateTime likedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RemoveLike {
        private Long memberId;
        private Long phraseId;

        @Builder.Default
        @JsonProperty("isLike")
        private boolean like = false;

        private int likeCount;
        private LocalDateTime canceledAt;
    }
}
