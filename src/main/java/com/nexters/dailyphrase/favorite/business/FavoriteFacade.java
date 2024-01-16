package com.nexters.dailyphrase.favorite.business;

import com.nexters.dailyphrase.favorite.implement.FavoriteCommandService;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteFacade {
    private final FavoriteQueryService favoriteQueryService;
    private final FavoriteCommandService favoriteCommandService;
}
