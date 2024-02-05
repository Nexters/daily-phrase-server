package com.nexters.dailyphrase.admin.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageListItem {
        private String imageUrl;
        private String imageRatio;
        private String fileName;
        private Long fileSize;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase {

        private String title;
        private String content;
        @Builder.Default private List<AdminRequestDTO.ImageListItem> images = new ArrayList<>();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPhrase {
        private String title;
        private String content;
        @Builder.Default private List<AdminRequestDTO.ImageListItem> images = new ArrayList<>();
        //        private String fileName;
        //        private String imageRatio;
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
