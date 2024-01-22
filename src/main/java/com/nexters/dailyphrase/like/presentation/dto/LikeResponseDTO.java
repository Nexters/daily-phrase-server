package com.nexters.dailyphrase.like.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

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
        private boolean isLike = true;
        private LocalDateTime likedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RemoveLike {
        private String field;
    }
}
