package com.nexters.dailyphrase.favorite.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.favorite.implement.FavoriteCommandService;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryService;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteRequestDTO;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FavoriteFacade {
    private final FavoriteQueryService favoriteQueryService;
    private final FavoriteCommandService favoriteCommandService;

    public FavoriteResponseDTO.AddFavorite addFavorite(FavoriteRequestDTO.AddFavorite request) {
        return null;
    }

    public FavoriteResponseDTO.RemoveFavorite removeLike(Long memberId, Long phraseId) {
        return null;
    }
}
