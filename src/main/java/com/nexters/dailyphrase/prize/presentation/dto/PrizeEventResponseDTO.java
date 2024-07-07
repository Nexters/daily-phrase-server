package com.nexters.dailyphrase.prize.presentation.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;

import io.swagger.v3.oas.annotations.media.Schema;
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
        private String shortName;
        private String manufacturer;
        private String welcomeImageUrl;
        private String bannerImageUrl;
        private String imageUrl;

        @Schema(description = "필요한 응모권 개수")
        private int requiredTicketCount;

        @Schema(description = "해당 경품에 응모한 전체 참여자 수")
        private long totalParticipantCount;

        @Schema(description = "내 응모 횟수")
        @Builder.Default
        private long myEntryCount = 0;

        @Schema(description = "내 응모권 수")
        @Builder.Default
        private long myTicketCount = 0;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeList {
        private long total;
        @Builder.Default private List<PrizeListItem> prizeList = new ArrayList<>();
        private LocalDateTime eventEndDateTime;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeEntryResult {
        private Long prizeId;
        private Long memberId;
        private PrizeEntryStatus status;
        private String messageTitle;
        private String messageDetail;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnterPhoneNumber {
        private Long prizeId;
        private Long memberId;
        private String phoneNumber;
    }
}
