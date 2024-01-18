package com.nexters.dailyphrase.common.presentation;

import java.time.LocalDateTime;

import com.nexters.dailyphrase.common.exception.ErrorReason;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final boolean isSuccess = false;
    private final int status;
    private final String code;
    private final String message;
    private final LocalDateTime timeStamp;
    private final String path;

    public ErrorResponse(ErrorReason errorReason, String path) {
        this.status = errorReason.getStatus();
        this.code = errorReason.getCode();
        this.message = errorReason.getReason();
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }

    public ErrorResponse(int status, String code, String reason, String path) {
        this.status = status;
        this.code = code;
        this.message = reason;
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }
}
