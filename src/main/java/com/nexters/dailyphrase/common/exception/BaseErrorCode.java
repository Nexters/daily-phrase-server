package com.nexters.dailyphrase.common.exception;

public interface BaseErrorCode {
    ErrorReason getErrorReason();

    String getExplainError() throws NoSuchFieldException;
}
