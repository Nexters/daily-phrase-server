package com.nexters.dailyphrase.member.implement.strategy;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoLoginStrategy implements SocialLoginStrategy {
    private final MemberRepository memberRepository;

    @Override
    public Member login(String identityToken) {
        return null;
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
