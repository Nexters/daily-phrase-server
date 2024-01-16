package com.nexters.dailyphrase.member.presentation;

import com.nexters.dailyphrase.member.business.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberFacade memberFacade;
}
