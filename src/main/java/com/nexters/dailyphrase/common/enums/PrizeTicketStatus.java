package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeTicketStatus {
    AVAILABLE("사용 가능"),
    USED("사용함");

    private final String description;
}
