package com.nexters.dailyphrase.member.implement.strategy;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.BEARER;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.infra.feign.kakao.client.KakaoLoginFeignClient;
import com.nexters.dailyphrase.infra.feign.kakao.dto.KakaoLoginUserDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoLoginStrategy implements SocialLoginStrategy {
    private final MemberRepository memberRepository;
    private final KakaoLoginFeignClient kakaoLoginFeignClient;

    @Override
    public Member login(String identityToken) {
        String accessTokenWithBearerPrefix = BEARER + identityToken;
        KakaoLoginUserDTO kakaoLoginUserDTO =
                kakaoLoginFeignClient.getInfo(accessTokenWithBearerPrefix);
        Member member =
                memberRepository
                        .findBySocialIdAndSocialType(kakaoLoginUserDTO.getId(), SocialType.KAKAO)
                        .orElseGet(kakaoLoginUserDTO::toMember);
        return memberRepository.save(member);
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
