package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeEventStatus {
    ACTIVE("진행중"),
    PENDING("대기"),
    CLOSED("종료");

    private final String description;
}
