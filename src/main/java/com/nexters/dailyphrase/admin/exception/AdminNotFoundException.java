package com.nexters.dailyphrase.admin.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class AdminNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new AdminNotFoundException();

    public AdminNotFoundException() {
        super(AdminErrorCode.ADMIN_NOT_FOUND);
    }
}
