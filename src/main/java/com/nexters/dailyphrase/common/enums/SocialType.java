package com.nexters.dailyphrase.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {
    KAKAO("kakao", "카카오");

    private final String value;
    private final String description;

    @JsonValue
    String getSocialType() {
        return this.name().toLowerCase();
    }

    public static SocialType findByValue(String value) {
        for (SocialType type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid SocialType value: " + value);
    }
}
