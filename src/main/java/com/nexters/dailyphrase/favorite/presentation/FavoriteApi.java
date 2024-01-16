package com.nexters.dailyphrase.favorite.presentation;

import com.nexters.dailyphrase.favorite.business.FavoriteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteFacade favoriteFacade;
}
