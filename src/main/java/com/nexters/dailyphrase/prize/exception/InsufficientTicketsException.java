package com.nexters.dailyphrase.prize.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class InsufficientTicketsException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new InsufficientTicketsException();

    private InsufficientTicketsException() {
        super(PrizeEventErrorCode.INSUFFICIENT_TICKETS);
    }
}
