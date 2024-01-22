package com.nexters.dailyphrase.like.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeRequestDTO {

    @Getter
    public static class AddLike {
        private Long memberId;
        private Long phraseId;
    }
}
