package com.nexters.dailyphrase.common.utils;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberUtils {
    private final MemberRepository memberRepository;

    public Long getCurrentMemberId() {
        return SecurityUtils.getCurrentMemberId();
    }

    public Optional<Member> getCurrentMember() {
        return memberRepository.findById(getCurrentMemberId());
    }
}
