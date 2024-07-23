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
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeEntryResult {
        private Long prizeId;
        private Long memberId;
        private PrizeEntryStatus status;
        //        NOTE - 모달 안내 문구는 클라이언트에서 처리 (문구 변경가능성 X)
        //        private String messageTitle;
        //        private String messageDetail;
        private String phoneNumber;
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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PrizeEventInfo {
        private Long eventId;
        private String name;
        private LocalDateTime eventStartDateTime;
        private LocalDateTime eventEndDateTime;
        private LocalDateTime eventWinnerAnnouncementDateTime;
        //        private String status;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnterPrizeEvent {
        private Long prizeId;
        private Long memberId;
        private PrizeEntryStatus status;
    }
}
