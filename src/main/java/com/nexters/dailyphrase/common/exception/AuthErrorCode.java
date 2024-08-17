package com.nexters.dailyphrase.common.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.*;

import java.lang.reflect.Field;
import java.util.Objects;

import com.nexters.dailyphrase.common.annotation.ExplainError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "인증 시간이 만료되었습니다. 인증토큰을 재 발급 해주세요"),
    INVALID_TOKEN(UNAUTHORIZED, "AUTH_401_2", "잘못된 토큰입니다. 재 로그인 해주세요"),
    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증 시간이 만료되었습니다. 재 로그인 해주세요."),
    ACCESS_TOKEN_NOT_EXIST(FORBIDDEN, "AUTH_403_2", "알맞은 accessToken을 넣어주세요."),
    SECURITY_CONTEXT_NOT_FOUND(INTERNAL_SERVER, "AUTH_500_1", "security context not found");

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
