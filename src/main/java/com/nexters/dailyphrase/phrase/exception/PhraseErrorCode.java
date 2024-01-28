package com.nexters.dailyphrase.phrase.exception;

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
public enum PhraseErrorCode implements BaseErrorCode {
    PHRASE_NOT_FOUND(NOT_FOUND, "PHRASE_404_1", "글귀를 찾을 수 없습니다.");

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
