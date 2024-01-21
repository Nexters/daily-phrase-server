package com.nexters.dailyphrase.member.business;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.member.implement.MemberCommandService;
import com.nexters.dailyphrase.member.implement.MemberQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    public MemberResponseDTO.LoginMember login(final SocialType socialType, final MemberRequestDTO.LoginMember request) {
        return null;
    }
}
