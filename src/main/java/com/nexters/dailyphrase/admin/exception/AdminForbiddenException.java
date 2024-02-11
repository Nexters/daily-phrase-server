package com.nexters.dailyphrase.admin.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class AdminForbiddenException extends BaseCodeException {

    public static BaseCodeException EXCEPTION = new AdminForbiddenException();

    public AdminForbiddenException() {
        super(AdminErrorCode.ADMIN_FORBIDDEN_EXCEPTION);
    }
}
