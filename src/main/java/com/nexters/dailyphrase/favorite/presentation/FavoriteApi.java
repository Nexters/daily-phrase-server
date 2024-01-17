package com.nexters.dailyphrase.favorite.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.favorite.business.FavoriteFacade;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteRequestDTO;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorites")
public class FavoriteApi {
    private final FavoriteFacade favoriteFacade;

    @PostMapping
    public CommonResponse<FavoriteResponseDTO.AddFavorite> addFavorite(
            @RequestBody final FavoriteRequestDTO.AddFavorite request) {
        return null;
    }

    @GetMapping("/members/{id}")
    public CommonResponse<FavoriteResponseDTO.FavoriteList> getFavoriteList(
            @PathVariable final Long id) {
        return null;
    }

    @DeleteMapping("/members/{memberId}/phrases/{phraseId}")
    public CommonResponse<FavoriteResponseDTO.RemoveFavorite> removeFavorite(
            @PathVariable final Long memberId, @PathVariable final Long phraseId) {
        return null;
    }
}
