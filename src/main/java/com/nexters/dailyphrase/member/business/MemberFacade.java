package com.nexters.dailyphrase.member.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.member.implement.MemberCommandService;
import com.nexters.dailyphrase.member.implement.MemberQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
}
