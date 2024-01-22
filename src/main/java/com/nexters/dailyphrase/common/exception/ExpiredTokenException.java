package com.nexters.dailyphrase.common.exception;

public class ExpiredTokenException extends BaseCodeException {
    public static final BaseCodeException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalErrorCode.TOKEN_EXPIRED);
    }
}
