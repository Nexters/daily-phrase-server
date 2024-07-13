package com.nexters.dailyphrase.share.presentation;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.share.business.ShareService;
import com.nexters.dailyphrase.share.presentation.dto.ShareRequestDTO;
import com.nexters.dailyphrase.share.presentation.dto.ShareResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "06-Share️🌐", description = "공유하기 관련 API")
@RestController
@RequestMapping("/api/v1/shares")
@RequiredArgsConstructor
public class ShareApi {

    private final ShareService shareService;

    @Operation(
            summary = "06-01 Share️🌐 글귀 공유하기 유저 데이터 기록 Made By 성훈",
            description = "글귀 공유하기 유저 데이터 기록 API입니다.")
    @PostMapping
    public CommonResponse<ShareResponseDTO.AddShare> addShare(
            @RequestBody final ShareRequestDTO.AddShare request) {
        return CommonResponse.onSuccess(shareService.addShare(request));
    }

    @Operation(
            summary = "06-02 Share️🌐 글귀 공유하기 내 공유하기 횟수 확인 Made By 성훈",
            description = "글귀 공유하기 내 공유하기 횟수 확인 API입니다.")
    @GetMapping("/me")
    public CommonResponse<ShareResponseDTO.MyShareCount> me(
            @RequestParam(required = false) LocalDate date) {
        return CommonResponse.onSuccess(
                shareService.getMyShareCount(date != null ? date : LocalDate.now()));
    }
}
