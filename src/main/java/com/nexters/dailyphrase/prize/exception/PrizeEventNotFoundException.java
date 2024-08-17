package com.nexters.dailyphrase.prize.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class PrizeEventNotFoundException extends BaseCodeException {

    public static BaseCodeException EXCEPTION = new PrizeEventNotFoundException();

    private PrizeEventNotFoundException() {
        super(PrizeEventErrorCode.PRIZE_EVENT_NOT_FOUND);
    }
}
