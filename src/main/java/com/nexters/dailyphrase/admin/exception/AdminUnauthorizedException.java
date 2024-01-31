package com.nexters.dailyphrase.admin.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class AdminUnauthorizedException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new AdminUnauthorizedException();

    public AdminUnauthorizedException() {
        super(AdminErrorCode.ADMIN_UNAUTHORIZED);
    }
}
