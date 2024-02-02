package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    GUEST("게스트"),
    USER("회원"),
    ADMIN("관리자");

    private final String description;
}
