package com.nexters.dailyphrase.member.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.implement.FavoriteCommandAdapter;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryAdapter;
import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.implement.LikeCommandAdapter;
import com.nexters.dailyphrase.like.implement.LikeQueryAdapter;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberCommandAdapter;
import com.nexters.dailyphrase.member.implement.MemberQueryAdapter;
import com.nexters.dailyphrase.member.implement.SocialLoginService;
import com.nexters.dailyphrase.member.implement.factory.SocialLoginServiceFactory;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberQueryAdapter memberQueryAdapter;
    private final MemberCommandAdapter memberCommandAdapter;
    private final LikeQueryAdapter likeQueryAdapter;
    private final LikeCommandAdapter likeCommandAdapter;
    private final FavoriteQueryAdapter favoriteQueryAdapter;
    private final FavoriteCommandAdapter favoriteCommandAdapter;
    private final SocialLoginServiceFactory socialLoginServiceFactory;
    private final MemberLoginActionProcessor memberLoginActionProcessor;
    private final JwtTokenService jwtTokenService;
    private final MemberMapper memberMapper;

    @Transactional
    public MemberResponseDTO.LoginMember login(
            final SocialType socialType, final MemberRequestDTO.LoginMember request) {
        SocialLoginService socialLoginService =
                socialLoginServiceFactory.getServiceBySocialType(socialType);
        Member member = socialLoginService.login(request.getIdentityToken());
        String accessToken =
                jwtTokenService.generateAccessToken(member.getId(), member.getRole().name());
        // TODO - Refresh 토큰을 Jwt로 발급하지 않고, Redis를 사용하는 방식도 고려하기
        String refreshToken = jwtTokenService.generateRefreshToken(member.getId());

        // NOTE - 회원가입 응모권 발급
        memberLoginActionProcessor.processLoginAction(member.getId());
        return memberMapper.toLoginMember(member, accessToken, refreshToken);
    }

    @Transactional
    public MemberResponseDTO.QuitMember quit(Long id) {
        Member member = memberQueryAdapter.findById(id);
        List<Long> likeIds = likeQueryAdapter.findByMemberId(id).stream().map(Like::getId).toList();
        likeCommandAdapter.deleteAllByIdInBatch(likeIds);
        List<Long> favoriteIds =
                favoriteQueryAdapter.findByMemberId(id).stream().map(Favorite::getId).toList();
        favoriteCommandAdapter.deleteAllByIdInBatch(favoriteIds);
        memberCommandAdapter.delete(member);
        return memberMapper.toQuitMember();
    }

    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberDetail getMemberDetail(Long id) {
        Member member = memberQueryAdapter.findById(id);
        return memberMapper.toMemberDetail(member);
    }
}
