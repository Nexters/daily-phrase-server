package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeEntryStatus {
    ENTERED("응모됨"),
    WINNING("당첨"),
    MISSED("미당첨");

    private final String description;
}
