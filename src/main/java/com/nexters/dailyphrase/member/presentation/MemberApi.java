package com.nexters.dailyphrase.member.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.annotation.ApiErrorCodeExample;
import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.common.exception.AuthErrorCode;
import com.nexters.dailyphrase.common.exception.FeignErrorCode;
import com.nexters.dailyphrase.common.exception.GlobalErrorCode;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.member.business.MemberFacade;
import com.nexters.dailyphrase.member.exception.MemberErrorCode;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "01-Member\uD83D\uDC64", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApi {
    private final MemberFacade memberFacade;

    @Operation(
            summary = "01-02 Member\uD83D\uDC64 회원 상세정보 조회 Made By 성훈",
            description = "회원 상세 정보 조회 API입니다.")
    @ApiErrorCodeExample(
            value = {MemberErrorCode.class, AuthErrorCode.class, GlobalErrorCode.class})
    @GetMapping("/{id}")
    public CommonResponse<MemberResponseDTO.MemberDetail> getMemberDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(memberFacade.getMemberDetail(id));
    }

    @Operation(
            summary = "01-03 Member\uD83D\uDC64 소셜 로그인 Made By 성훈",
            description = "KAKAO 소셜로그인 API입니다.")
    @ApiErrorCodeExample(value = {FeignErrorCode.class, GlobalErrorCode.class})
    @CrossOrigin(allowCredentials = "false", allowedHeaders = "*", origins = "*", maxAge = 6000, methods = RequestMethod.POST)
    @PostMapping("/login/{socialType}")
    public CommonResponse<MemberResponseDTO.LoginMember> login(
            @PathVariable final SocialType socialType,
            @RequestBody final MemberRequestDTO.LoginMember request) {
        return CommonResponse.onSuccess(memberFacade.login(socialType, request));
    }

    @Operation(summary = "01-01 Member\uD83D\uDC64 회원 탈퇴 Made By 성훈", description = "회원 탈퇴 API입니다.")
    @DeleteMapping("/{id}")
    @ApiErrorCodeExample(
            value = {MemberErrorCode.class, AuthErrorCode.class, GlobalErrorCode.class})
    public CommonResponse<MemberResponseDTO.QuitMember> quit(@PathVariable final Long id) {
        return CommonResponse.onSuccess(memberFacade.quit(id));
    }
}
