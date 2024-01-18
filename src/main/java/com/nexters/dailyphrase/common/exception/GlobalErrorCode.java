package com.nexters.dailyphrase.common.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    ARGUMENT_NOT_VALID_ERROR(BAD_REQUEST, "GLOBAL_400_1", "검증 오류"),

    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "인증 시간이 만료되었습니다. 인증토큰을 재 발급 해주세요"),
    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증 시간이 만료되었습니다. 재 로그인 해주세요."),
    ACCESS_TOKEN_NOT_EXIST(FORBIDDEN, "AUTH_403_2", "알맞은 accessToken을 넣어주세요."),
    INVALID_TOKEN(UNAUTHORIZED, "GLOBAL_401_1", "잘못된 토큰입니다. 재 로그인 해주세요"),
    INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "GLOBAL_500_1", "서버 오류. 관리자에게 문의 부탁드립니다."),

    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "FEIGN_400_1", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED(BAD_REQUEST, "FEIGN_400_2", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN(BAD_REQUEST, "FEIGN_400_3", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "FEIGN_400_4", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "FEIGN_400_5", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR(
            BAD_REQUEST, "FEIGN_400_6", "Other server internal server error"),
    SECURITY_CONTEXT_NOT_FOUND(500, "GLOBAL_500_2", "security context not found"),

    BAD_FILE_EXTENSION(BAD_REQUEST, "FILE_400_1", "파일 확장자가 잘못 되었습니다."),
    TOO_MANY_REQUEST(429, "GLOBAL_429_1", "과도한 요청을 보내셨습니다. 잠시 기다려 주세요.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
