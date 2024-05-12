package com.nexters.dailyphrase.share.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.share.business.ShareService;
import com.nexters.dailyphrase.share.presentation.dto.KakaolinkCallbackRequestDTO;
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
    private static final String VALID_API_KEY = "your_service_app_admin_key"; // 실제 어드민 키

    @Operation(
            summary = "06-01 Share️🌐 글귀 공유하기 유저 데이터 기록 Made By 성훈",
            description = "글귀 공유하기 유저 데이터 기록 API입니다.")
    @PostMapping
    public CommonResponse<ShareResponseDTO.AddShare> addShare(
            @RequestBody final ShareRequestDTO.AddShare request) {
        return CommonResponse.onSuccess(shareService.addShare(request));
    }

    @PostMapping("/kakaolink/callback")
    public ResponseEntity<String> handleKakaoLinkCallback(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("X-Kakao-Resource-ID") String kakaoResourceId,
            @RequestHeader("User-Agent") String userAgent,
            @RequestBody KakaolinkCallbackRequestDTO request) {
        return ResponseEntity.ok("Received");
    }
}
