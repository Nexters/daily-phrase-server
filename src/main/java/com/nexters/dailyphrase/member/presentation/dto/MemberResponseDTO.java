package com.nexters.dailyphrase.member.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginMember {
        private Long memberId;
        private String accessToken;
        private String refreshToken;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuitMember {
        private LocalDateTime quitAt;
    }
}
