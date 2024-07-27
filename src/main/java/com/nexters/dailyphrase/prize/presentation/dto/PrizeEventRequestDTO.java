package com.nexters.dailyphrase.prize.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrizeEventRequestDTO {

    @Getter
    public static class EnterPhoneNumber {
        private String phoneNumber;
    }

    @Getter
    public static class EnterPrizeEvent {
        private Long prizeId;
    }

    @Getter
    public static class CheckPrizeEntryResult {
        private Long prizeId;
    }
}
