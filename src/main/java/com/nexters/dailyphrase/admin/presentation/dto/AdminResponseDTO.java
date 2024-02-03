package com.nexters.dailyphrase.admin.presentation.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogoutAdmin {
        private String field;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginAdmin {
        private String userId;
        private String accessToken;
        private String refreshToken;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase {

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
    public static class AdminPhraseListItem {
        private Long phraseId;
        private String title;
        private String content;
        private String filename;
        private LocalDateTime createdAt;
        private int viewCount;
        private int likeCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminPhraseList {
        @Builder.Default
        private List<AdminResponseDTO.AdminPhraseListItem> phraseList = new ArrayList<>();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeletePhrase {
        private LocalDateTime deletedAt;
    }
}
