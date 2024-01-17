package com.nexters.dailyphrase.member.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.member.business.MemberFacade;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApi {
    private final MemberFacade memberFacade;

    @PostMapping("/login/{socialType}")
    public CommonResponse<MemberResponseDTO.LoginMember> login(
            @PathVariable final SocialType socialType,
            @RequestBody final MemberRequestDTO.LoginMember request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public CommonResponse<MemberResponseDTO.ExitMember> exit(@PathVariable final Long id) {
        return null;
    }
}
