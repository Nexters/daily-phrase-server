package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeTicketSource {
    SIGNUP("회원가입"),
    SHARE("공유하기");

    private final String description;
}
