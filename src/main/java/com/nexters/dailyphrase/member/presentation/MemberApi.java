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

    @GetMapping("/{id}")
    public CommonResponse<MemberResponseDTO.MemberDetail> getMemberDetail(@PathVariable final Long id) {
        return CommonResponse.onSuccess(memberFacade.getMemberDetail(id));
    }

    @PostMapping("/login/{socialType}")
    public CommonResponse<MemberResponseDTO.LoginMember> login(
            @PathVariable final SocialType socialType,
            @RequestBody final MemberRequestDTO.LoginMember request) {
        return CommonResponse.onSuccess(memberFacade.login(socialType, request));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<MemberResponseDTO.QuitMember> quit(@PathVariable final Long id) {
        return CommonResponse.onSuccess(memberFacade.quit(id));
    }
}
