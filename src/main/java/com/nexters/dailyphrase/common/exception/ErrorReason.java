package com.nexters.dailyphrase.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
    private final Integer status;
    private final String code;
    private final String reason;
}
