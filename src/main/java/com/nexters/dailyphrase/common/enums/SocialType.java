package com.nexters.dailyphrase.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    KAKAO("kakao", "카카오"),
    GOOGLE("google", "구글"),
    APPLE("apple", "애플");

    private final String value;
    private final String description;

    @JsonValue
    String getSocialType() {
        return this.name().toLowerCase();
    }
}
