package com.nexters.dailyphrase.common.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

    private final String secretKey;
    private final long accessExp;
    private final long refreshExp;

    public JwtProperties(String secretKey, long accessExp, long refreshExp) {
        this.secretKey = secretKey;
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
    }
}
