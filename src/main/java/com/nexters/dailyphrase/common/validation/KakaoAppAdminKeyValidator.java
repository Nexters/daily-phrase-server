package com.nexters.dailyphrase.common.validation;

import org.springframework.beans.factory.annotation.Value;

import com.nexters.dailyphrase.common.annotation.Validator;

import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class KakaoAppAdminKeyValidator {

    @Value("${kakao.app-admin-key}")
    private String KAKAO_APP_ADMIN_KEY;

    private String extractRequestKey(final String authorizationHeader) {
        String prefix = "KakaoAK ";

        if (authorizationHeader.startsWith(prefix)) {
            return authorizationHeader.substring(prefix.length()).trim();
        }

        return authorizationHeader;
    }

    public boolean isValid(final String authorizationHeader) {
        final String requestKey = extractRequestKey(authorizationHeader);
        return KAKAO_APP_ADMIN_KEY.equals(requestKey);
    }
}
