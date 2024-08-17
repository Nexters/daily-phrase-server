package com.nexters.dailyphrase.member.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRequestDTO {
    @Getter
    public static class LoginMember {
        private String identityToken;
    }
}
