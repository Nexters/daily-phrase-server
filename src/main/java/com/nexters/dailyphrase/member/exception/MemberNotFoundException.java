package com.nexters.dailyphrase.member.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class MemberNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new MemberNotFoundException();

    private MemberNotFoundException() {
        super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
}
