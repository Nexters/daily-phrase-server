package com.nexters.dailyphrase.admin.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.admin.business.AdminFacade;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "05-Admin️👷🏻", description = "관리자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {
    private final AdminFacade adminFacade;

    @Operation(summary = "05-07 Admin👷🏻 관리자 로그인 Made By 채은", description = "관리자 로그인 API입니다.")
    @PostMapping("/login")
    public CommonResponse<AdminResponseDTO.LoginAdmin> loginAdmin(
            @RequestBody final AdminRequestDTO.LoginAdmin request) {
        return CommonResponse.onSuccess(adminFacade.loginAdmin(request));
    }

    @Operation(
            summary = "05-07 Admin👷🏻 관리자 로그인 토큰 재발행 Made By 채은",
            description = "관리자 로그인 토큰 재발행 API입니다.")
    @PostMapping("/reissue")
    public void reissueToken() {}

    @Operation(summary = "05-06 Admin️👷🏻 관리자 로그아웃 Made By 채은", description = "관리자 로그아웃 API입니다.")
    @PostMapping("/logout")
    public CommonResponse<AdminResponseDTO.LogoutAdmin> logoutAdmin() {
        return null;
    }

    @Operation(
            summary = "05-02 Admin️👷🏻 관리자 글귀 목록 조회 Made By 채은",
            description = "관리자 글귀 목록 조회 API입니다.")
    @GetMapping("/phrases")
    public CommonResponse<PhraseResponseDTO.PhraseList> getAdminPhraseList() {
        return null;
    }

    @Operation(
            summary = "05-03 Admin️👷🏻 관리자 글귀 상세 조회 Made By 채은",
            description = "관리자 글귀 상세 조회 API입니다.")
    @GetMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.AdminPhraseDetail> getAdminPhraseDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(adminFacade.getAdminPhraseDetail(id));
    }

    @Operation(summary = "05-05 Admin️👷🏻 관리자 글귀 등록 Made By 채은", description = "관리자 글귀 등록 API입니다.")
    @PostMapping("/phrases")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<AdminResponseDTO.AddPhrase> addPhrase(
            @RequestBody final AdminRequestDTO.AddPhrase request) {
        return CommonResponse.onSuccess(adminFacade.addPhrase(request));
    }

    @Operation(summary = "05-04 Admin️👷🏻 관리자 글귀 수정 Made By 채은", description = "관리자 글귀 수정 API입니다.")
    @PatchMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.ModifyPhrase> modifyPhrase(
            @PathVariable Long id, @RequestBody final AdminRequestDTO.ModifyPhrase request) {
        return CommonResponse.onSuccess(adminFacade.modifyPhrase(id, request));
    }

    @Operation(summary = "05-01 Admin️👷🏻 관리자 글귀 삭제 Made By 채은", description = "관리자 글귀 삭제 API입니다.")
    @DeleteMapping("/phrases/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse<AdminResponseDTO.DeletePhrase> deletePhrase(@PathVariable final Long id) {
        return CommonResponse.onSuccess(adminFacade.deletePhrase(id));
    }
}
