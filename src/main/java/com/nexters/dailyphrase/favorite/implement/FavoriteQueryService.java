package com.nexters.dailyphrase.favorite.implement;

import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {
    private final FavoriteRepository favoriteRepository;
}
