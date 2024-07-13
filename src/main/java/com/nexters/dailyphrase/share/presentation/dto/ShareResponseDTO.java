package com.nexters.dailyphrase.share.presentation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShareResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddShare {
        private Long memberId;
        private Long phraseId;
        private LocalDateTime sharedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyShareCount {
        private Integer shareCount;
        private LocalDate date;
    }
}
