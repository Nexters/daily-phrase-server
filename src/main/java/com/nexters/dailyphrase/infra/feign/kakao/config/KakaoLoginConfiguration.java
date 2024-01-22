package com.nexters.dailyphrase.infra.feign.kakao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Import(KakaoLoginErrorDecoder.class)
public class KakaoLoginConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Content-Type", "application/x-www-form-urlencoded");
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new KakaoLoginErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
