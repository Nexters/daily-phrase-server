package com.nexters.dailyphrase.favorite.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class DuplicateFavoriteException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new DuplicateFavoriteException();

    private DuplicateFavoriteException() {
        super(FavoriteErrorCode.DUPLICATE_FAVORITE);
    }
}
