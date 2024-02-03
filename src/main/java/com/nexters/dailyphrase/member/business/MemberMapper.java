package com.nexters.dailyphrase.member.business;

import java.time.LocalDateTime;
import java.util.Optional;

import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

@Component
public class MemberMapper {

    private final MemberRepository memberRepository;

    public MemberMapper(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
                .profileImgUrl(profileImgUrlgi
                .build();
    }

    public MemberResponseDTO.QuitMember toQuitMember() {
        return MemberResponseDTO.QuitMember.builder().quitAt(LocalDateTime.now()).build();
    }
}
