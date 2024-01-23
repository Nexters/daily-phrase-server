package com.nexters.dailyphrase.admin.presentation.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogoutAdmin{
        private String field;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginAdmin{
        private String field;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase{

        private Long id;
        private LocalDateTime createdAt;
//        private String imageRatio; 목록 조회할때 나오면 될듯
//        private String imageUrl;
//        private String uuid;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPhrase {
        private String field;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeletePhrase {
        private String field;
    }
}
