package com.nexters.dailyphrase.infra.feign.kakao.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

@Import(KakaoLoginErrorDecoder.class)
public class KakaoLoginConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public KakaoLoginErrorDecoder commonFeignErrorDecoder() {
        return new KakaoLoginErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
