package com.nexters.dailyphrase.member.implement;

import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }
}
