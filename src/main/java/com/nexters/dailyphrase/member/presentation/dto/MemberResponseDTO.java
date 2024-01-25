package com.nexters.dailyphrase.member.presentation.dto;

import java.time.LocalDateTime;

import lombok.*;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberDetail {
        private Long memberId;
        private String name;
        private String email;
    }
}
