package com.nexters.dailyphrase.member.presentation;

import org.springframework.web.bind.annotation.RestController;

import com.nexters.dailyphrase.member.business.MemberFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberFacade memberFacade;
}
