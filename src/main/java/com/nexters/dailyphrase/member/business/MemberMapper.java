package com.nexters.dailyphrase.member.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

@Component
public class MemberMapper {
    public MemberResponseDTO.LoginMember toLoginMember(
            Member member, String accessToken, String refreshToken) {
        return MemberResponseDTO.LoginMember.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
