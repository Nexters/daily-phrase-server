package com.nexters.dailyphrase.member.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberCommandService;
import com.nexters.dailyphrase.member.implement.MemberQueryService;
import com.nexters.dailyphrase.member.implement.SocialLoginService;
import com.nexters.dailyphrase.member.implement.factory.SocialLoginServiceFactory;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final SocialLoginServiceFactory socialLoginServiceFactory;
    private final MemberMapper memberMapper;

    public MemberResponseDTO.LoginMember login(
            final SocialType socialType, final MemberRequestDTO.LoginMember request) {
        SocialLoginService socialLoginService =
                socialLoginServiceFactory.getServiceBySocialType(socialType);
        Member member = socialLoginService.login(request.getIdentityToken());
        String accessToken = ""; // TODO : redis service에서 RefreshToken 발급
        String refreshToken = ""; // TODO : token service에서 AccessToken 발급
        return memberMapper.toLoginMember(member, accessToken, refreshToken);
    }
}
