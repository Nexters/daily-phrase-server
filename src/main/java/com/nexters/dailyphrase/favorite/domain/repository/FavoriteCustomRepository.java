package com.nexters.dailyphrase.favorite.domain.repository;

import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

public interface FavoriteCustomRepository {
    FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId);
}
