package com.nexters.dailyphrase.prize.presentation.dto;

import jakarta.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrizeEventRequestDTO {

    @Getter
    public static class EnterPhoneNumber {
        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호는 XXX-XXXX-XXXX 형식이어야 합니다.")
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
