package com.nexters.dailyphrase.favorite.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class FavoriteNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new FavoriteNotFoundException();

    private FavoriteNotFoundException() {
        super(FavoriteErrorCode.FAVORITE_NOT_FOUND);
    }
}
