package com.nexters.dailyphrase.like.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class LikeNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new LikeNotFoundException();

    private LikeNotFoundException() {
        super(LikeErrorCode.LIKE_NOT_FOUND);
    }
}
