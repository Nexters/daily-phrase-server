package com.nexters.dailyphrase.member.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class MemberCommandAdapter {
    private final MemberRepository memberRepository;

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
