package com.nexters.dailyphrase.member.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.member.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;
}
