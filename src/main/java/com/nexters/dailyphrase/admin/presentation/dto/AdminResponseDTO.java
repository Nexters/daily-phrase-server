package com.nexters.dailyphrase.admin.presentation.dto;

import java.time.LocalDateTime;
import lombok.*;

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
    public static class AdminPhraseDetail {
        private String title;
        private String imageUrl;
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPhrase {
        private Long id;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;
        private String title;
        private String imageUrl;
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeletePhrase {
        private String field;
    }
}
