package com.nexters.dailyphrase.admin.presentation.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase {

        private String title;
        private String content;
        // private String imageRatio;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPhrase {
        private String title;
        private String content;
        private String fileName;
        private String imageRatio;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginAdmin {
        private String userId;
        private String password;
    }
}
