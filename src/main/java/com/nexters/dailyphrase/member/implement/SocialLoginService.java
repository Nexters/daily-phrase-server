package com.nexters.dailyphrase.member.implement;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.strategy.SocialLoginStrategy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SocialLoginService {
    private final SocialLoginStrategy socialLoginStrategy;

    public Member login(String identityToken) {
        return this.socialLoginStrategy.login(identityToken);
    }
}
