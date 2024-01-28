package com.nexters.dailyphrase.favorite.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FavoriteRequestDTO {
    @Getter
    public static class AddFavorite {
        private Long memberId;
        private Long phraseId;
    }
}
