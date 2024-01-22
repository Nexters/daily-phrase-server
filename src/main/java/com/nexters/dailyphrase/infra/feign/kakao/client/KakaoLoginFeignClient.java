package com.nexters.dailyphrase.infra.feign.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.nexters.dailyphrase.infra.feign.kakao.config.KakaoLoginConfig;
import com.nexters.dailyphrase.infra.feign.kakao.dto.KakaoLoginUserDTO;

@FeignClient(
        name = "KakaoLoginFeignClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoLoginConfig.class)
@Component
public interface KakaoLoginFeignClient {
    @GetMapping("/v2/user/me")
    KakaoLoginUserDTO getInfo(@RequestHeader(name = "Authorization") String identityToken);
}
