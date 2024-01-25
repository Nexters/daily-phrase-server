package com.nexters.dailyphrase.member.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
