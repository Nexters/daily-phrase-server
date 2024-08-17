package com.nexters.dailyphrase.common.presentation;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexters.dailyphrase.common.exception.ErrorReason;

import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "status", "timeStamp", "path"})
public class ErrorResponse {

    @JsonProperty("isSuccess")
    private final boolean success;

    private final int status;
    private final String code;
    private final String message;
    private final LocalDateTime timeStamp;
    private final String path;

    public ErrorResponse(ErrorReason errorReason, String path) {
        this.success = false;
        this.status = errorReason.getStatus();
        this.code = errorReason.getCode();
        this.message = errorReason.getReason();
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }

    public ErrorResponse(int status, String code, String reason, String path) {
        this.success = false;
        this.status = status;
        this.code = code;
        this.message = reason;
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }
}
