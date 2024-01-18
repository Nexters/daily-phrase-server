package com.nexters.dailyphrase.phrase.exception;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

public class PhraseNotFoundException extends BaseCodeException {
    public static BaseCodeException EXCEPTION = new PhraseNotFoundException();

    private PhraseNotFoundException() {
        super(PhraseErrorCode.PHRASE_NOT_FOUND);
    }
}
