package com.nexters.dailyphrase.member.exception;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.NOT_FOUND;

import com.nexters.dailyphrase.common.exception.BaseErrorCode;
import com.nexters.dailyphrase.common.exception.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER_404_1", "회원을 찾을 수 없습니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
