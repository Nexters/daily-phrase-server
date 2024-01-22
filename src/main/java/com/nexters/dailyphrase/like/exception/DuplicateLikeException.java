package com.nexters.dailyphrase.like.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class DuplicateLikeException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new DuplicateLikeException();

    private DuplicateLikeException() {
        super(LikeErrorCode.DUPLICATE_LIKE);
    }
}
