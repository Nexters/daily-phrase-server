package com.nexters.dailyphrase.infra.feign.kakao.config;

import static com.nexters.dailyphrase.common.exception.GlobalErrorCode.*;

import com.nexters.dailyphrase.common.exception.BaseCodeException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoLoginErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            System.out.println(response.toString());
            switch (response.status()) {
                case 401 -> throw new BaseCodeException(OTHER_SERVER_UNAUTHORIZED);
                case 403 -> throw new BaseCodeException(OTHER_SERVER_FORBIDDEN);
                case 419 -> throw new BaseCodeException(OTHER_SERVER_EXPIRED_TOKEN);
                default -> throw new BaseCodeException(OTHER_SERVER_BAD_REQUEST);
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
