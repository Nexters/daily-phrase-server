package com.nexters.dailyphrase.common.exception;

public class RefreshTokenExpiredException extends BaseCodeException {
    public static final BaseCodeException EXCEPTION = new RefreshTokenExpiredException();

    private RefreshTokenExpiredException() {
        super(AuthErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
