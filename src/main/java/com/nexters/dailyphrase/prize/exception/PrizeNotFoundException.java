package com.nexters.dailyphrase.prize.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class PrizeNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new PrizeNotFoundException();

    private PrizeNotFoundException() {
        super(PrizeEventErrorCode.PRIZE_NOT_FOUND);
    }
}
