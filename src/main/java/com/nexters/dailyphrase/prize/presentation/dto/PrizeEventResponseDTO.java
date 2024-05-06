package com.nexters.dailyphrase.prize.presentation.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrizeEventResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeListItem {
        private Long prizeId;
        private Long eventId;
        private String name;
        private String imageUrl;
        private int requiredTicketCount; // 필요한 응모권 개수
        private int totalEntryCount; // 전체 응모수
        @Builder.Default private int myEntryCount = 0; // 내 응모수
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeList {
        private long total;
        @Builder.Default private List<PrizeListItem> prizeList = new ArrayList<>();
    }
}
