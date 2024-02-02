package com.nexters.dailyphrase.admin.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.NOT_FOUND;
import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.UNAUTHORIZED;

import java.lang.reflect.Field;
import java.util.Objects;

import com.nexters.dailyphrase.common.annotation.ExplainError;
import com.nexters.dailyphrase.common.exception.BaseErrorCode;
import com.nexters.dailyphrase.common.exception.ErrorReason;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminErrorCode implements BaseErrorCode {
    ADMIN_NOT_FOUND(NOT_FOUND, "ADMIN_404_1", "해당 관리자를 찾을 수 없습니다."),
    ADMIN_BAD_CREDENTIALS_EXCEPTION(UNAUTHORIZED, "ADMIN_401_1", "자격증명이 없습니다. 아이디 또는 비밀번호가 틀렸습니다.");

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
