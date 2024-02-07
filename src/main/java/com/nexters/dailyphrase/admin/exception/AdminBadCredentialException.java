package com.nexters.dailyphrase.admin.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class AdminBadCredentialException extends BaseCodeException {

    public static BaseCodeException EXCEPTION = new AdminBadCredentialException();

    public AdminBadCredentialException() {
        super(AdminErrorCode.ADMIN_BAD_CREDENTIALS_EXCEPTION);
    }
}
