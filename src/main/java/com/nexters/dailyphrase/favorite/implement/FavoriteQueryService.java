package com.nexters.dailyphrase.favorite.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {
    private final FavoriteRepository favoriteRepository;
}
