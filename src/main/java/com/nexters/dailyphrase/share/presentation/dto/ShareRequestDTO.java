package com.nexters.dailyphrase.share.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShareRequestDTO {

    @Getter
    public static class AddShare {
        private Long memberId;
        private Long phraseId;
    }
}
