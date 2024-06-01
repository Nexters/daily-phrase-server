package com.nexters.dailyphrase.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeEntryStatus {
    ENTERED("응모됨", "", ""),
    WINNING("당첨", "당첨을 축하해요!", "남겨주신 연락처로 선물을 받을 수 있는 링크를 전달해드릴게요."),
    MISSED("미당첨", "아쉽게도 당첨되지 않았어요.", "");

    private final String description;
    private final String messageTitle;
    private final String messageDetail;
}
