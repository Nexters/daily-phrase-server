package com.nexters.dailyphrase.like.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.DUPLICATE;
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
public enum LikeErrorCode implements BaseErrorCode {
    LIKE_NOT_FOUND(NOT_FOUND, "LIKE_404_1", "좋아요 기록이 없습니다."),
    DUPLICATE_LIKE(DUPLICATE, "LIKE_409_1", "이미 좋아요한 글귀입니다.");

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
