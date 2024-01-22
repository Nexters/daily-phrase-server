package com.nexters.dailyphrase.admin.presentation.dto;

import lombok.*;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminRequestDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddPhrase {

        String title;
        String content;
        int viewCount;
        String imageUrl;
        String fileName;
        String uuid;
       // private int LikeCount;

    }


    @Getter
    public static class ModifyPhrase {
        private String field;
    }
}
