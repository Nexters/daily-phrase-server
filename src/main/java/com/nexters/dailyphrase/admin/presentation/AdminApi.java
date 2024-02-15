package com.nexters.dailyphrase.admin.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nexters.dailyphrase.admin.business.AdminFacade;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.presentation.CommonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "05-Admin️👷🏻", description = "관리자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {
    private final AdminFacade adminFacade;
    @CrossOrigin(allowCredentials = "false", allowedHeaders = "*", origins = "*", maxAge = 6000, methods = RequestMethod.POST)
    @Operation(summary = "05-07 Admin👷🏻 관리자 로그인 Made By 채은", description = "관리자 로그인 API입니다.")
    @PostMapping("/login")
    public CommonResponse<AdminResponseDTO.LoginAdmin> loginAdmin(
            @RequestBody final AdminRequestDTO.LoginAdmin request) {
        return CommonResponse.onSuccess(adminFacade.loginAdmin(request));
    }

    @Operation(
            summary = "05-08 Admin👷🏻 관리자 로그인 토큰 재발행 Made By 채은",
            description = "관리자 로그인 토큰 재발행 API입니다.")
    @PostMapping("/reissue")
    public void reissueToken() {}

    @Operation(
            summary = "05-02 Admin️👷🏻 관리자 글귀 목록 조회 Made By 채은",
            description = "관리자 글귀 목록 조회 API입니다.")
    @GetMapping("/phrases")
    public CommonResponse<AdminResponseDTO.AdminPhraseList> getAdminPhraseList() {
        return CommonResponse.onSuccess(adminFacade.getAdminPhraseList());
    }

    @Operation(
            summary = "05-03 Admin️👷🏻 관리자 글귀 상세 조회 Made By 채은",
            description = "관리자 글귀 상세 조회 API입니다.")
    @GetMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.AdminPhraseDetail> getAdminPhraseDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(adminFacade.getAdminPhraseDetail(id));
    }

    @Operation(summary = "05-05 Admin👷🏻 관리자 글귀 등록 Made By 채은", description = "관리자 글귀 등록 API입니다.")
    @PostMapping("/phrases")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<AdminResponseDTO.AddPhrase> addPhrase(
            @RequestBody final AdminRequestDTO.AddPhrase request) throws Exception {
        return CommonResponse.onSuccess(adminFacade.addPhrase(request));
    }

    // 이미지 파일 다건일때(주석처리)
    //    @Operation(
    //            summary = "05-05 Admin👷🏻 관리자 이미지 파일 업로드 Made By 채은",
    //            description = "관리자 이미지 파일 업로드 API입니다.")
    //    @PostMapping("/phrases/upload")
    //    @ResponseStatus(HttpStatus.CREATED)
    //    public CommonResponse<AdminResponseDTO.UploadImageFiles> uploadImageFiles(
    //            @RequestParam final List<MultipartFile> images) throws Exception {
    //        return CommonResponse.onSuccess(adminFacade.uploadImageFiles(images));
    //    }

    @Operation(
            summary = "05-05 Admin👷🏻 관리자 이미지 파일 업로드 Made By 채은",
            description = "관리자 이미지 파일 업로드 API입니다.")
    @PostMapping("/phrases/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<AdminResponseDTO.UploadImageFile> uploadImageFile(
            @RequestParam final MultipartFile image) throws Exception {
        return CommonResponse.onSuccess(adminFacade.uploadImageFile(image));
    }

    @Operation(summary = "05-04 Admin️👷🏻 관리자 글귀 수정 Made By 채은", description = "관리자 글귀 수정 API입니다.")
    @PatchMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.ModifyPhrase> modifyPhrase(
            @PathVariable Long id, @RequestBody final AdminRequestDTO.ModifyPhrase request) {
        return CommonResponse.onSuccess(adminFacade.modifyPhrase(id, request));
    }

    @Operation(summary = "05-01 Admin️👷🏻 관리자 글귀 삭제 Made By 채은", description = "관리자 글귀 삭제 API입니다.")
    @DeleteMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.DeletePhrase> deletePhrase(@PathVariable final Long id) {
        return CommonResponse.onSuccess(adminFacade.deletePhrase(id));
    }
}
