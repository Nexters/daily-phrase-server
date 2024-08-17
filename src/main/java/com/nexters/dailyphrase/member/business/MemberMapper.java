package com.nexters.dailyphrase.member.business;

import java.time.LocalDateTime;
import java.util.Optional;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

@Mapper
public class MemberMapper {

    public MemberResponseDTO.LoginMember toLoginMember(
            Member member, String accessToken, String refreshToken) {
        return MemberResponseDTO.LoginMember.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public MemberResponseDTO.MemberDetail toMemberDetail(Member member) {
        String profileImgUrl = Optional.ofNullable(member.getProfileImgUrl()).orElse("");
        return MemberResponseDTO.MemberDetail.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profileImgUrl(profileImgUrl)
                .build();
    }

    public MemberResponseDTO.QuitMember toQuitMember() {
        return MemberResponseDTO.QuitMember.builder().quitAt(LocalDateTime.now()).build();
    }
}
