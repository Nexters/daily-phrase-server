package com.nexters.dailyphrase.favorite.presentation;

import org.springframework.web.bind.annotation.RestController;

import com.nexters.dailyphrase.favorite.business.FavoriteFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteFacade favoriteFacade;
}
