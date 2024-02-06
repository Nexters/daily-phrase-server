package com.nexters.dailyphrase.admin.presentation.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminRequestDTO {

    // 이미지 다건일때
    //    @Builder
    //    @Getter
    //    @NoArgsConstructor
    //    @AllArgsConstructor
    //    public static class ImageListItem {
    //        private String imageUrl;
    //        private String imageRatio;
    //        private String fileName;
    //        private Long fileSize;
    //    }
    //
    //    @Builder
    //    @Getter
    //    @NoArgsConstructor
    //    @AllArgsConstructor
    //    public static class AddPhrase {
    //
    //        private String title;
    //        private String content;
    //        @Builder.Default private List<AdminRequestDTO.ImageListItem> images = new
    // ArrayList<>();
    //    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase {

        private String title;
        private String content;
        private String imageUrl;
        private String imageRatio;
        private String fileName;
        private Long fileSize;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPhrase {
        private String title;
        private String content;
        //        @Builder.Default private List<AdminRequestDTO.ImageListItem> images = new
        // ArrayList<>(); //이미지 다건일때
        private String imageUrl;
        private String imageRatio;
        private String fileName;
        private Long fileSize;
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
