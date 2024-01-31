package com.nexters.dailyphrase.admin.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class AdminPasswordInvalidException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new AdminPasswordInvalidException();

    public AdminPasswordInvalidException() {
        super(AdminErrorCode.ADMIN_PASSWORD_INVALID);
    }
}
