package com.nexters.dailyphrase.member.business;

import com.nexters.dailyphrase.member.implement.MemberCommandService;
import com.nexters.dailyphrase.member.implement.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
}
