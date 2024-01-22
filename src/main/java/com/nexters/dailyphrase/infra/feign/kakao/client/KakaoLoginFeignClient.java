package com.nexters.dailyphrase.infra.feign.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.nexters.dailyphrase.infra.feign.kakao.config.KakaoLoginConfig;
import com.nexters.dailyphrase.infra.feign.kakao.dto.KakaoLoginUserDTO;

@FeignClient(
        name = "KakaoInfoClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoLoginConfig.class)
@Component
// Token을 가지고 회원정보를 요청하는 Feign이다.
public interface KakaoLoginFeignClient {
    @GetMapping("/v2/user/me")
    KakaoLoginUserDTO getInfo(@RequestHeader(name = "Authorization") String Authorization);
}
