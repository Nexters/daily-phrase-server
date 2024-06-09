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
}
