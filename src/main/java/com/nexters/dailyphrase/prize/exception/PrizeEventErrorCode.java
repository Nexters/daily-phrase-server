package com.nexters.dailyphrase.prize.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.BAD_REQUEST;
import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.NOT_FOUND;

import java.lang.reflect.Field;
import java.util.Objects;

import com.nexters.dailyphrase.common.annotation.ExplainError;
import com.nexters.dailyphrase.common.exception.BaseErrorCode;
import com.nexters.dailyphrase.common.exception.ErrorReason;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrizeEventErrorCode implements BaseErrorCode {
    PRIZE_EVENT_NOT_FOUND(NOT_FOUND, "PRIZE_EVENT_404_1", "존재하지 않는 이벤트입니다."),
    PRIZE_NOT_FOUND(NOT_FOUND, "PRIZE_404_1", "존재하지 않는 경품입니다."),
    INSUFFICIENT_TICKETS(BAD_REQUEST, "PRIZE_TICKET_400_1", "티켓이 부족합니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
