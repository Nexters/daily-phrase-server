package com.nexters.dailyphrase.prize.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.prize.business.PrizeEventService;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.nexters.dailyphrase.share.presentation.dto.KakaolinkCallbackRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "07-Event 🎁", description = "경품 응모 이벤트 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class PrizeEventApi {

    private final PrizeEventService prizeEventService;

    @Operation(
            summary = "07-01 Event 🎁 특정 이벤트의 경품 목록 조회 Made By 성훈",
            description = "특정 이벤트의 경품 목록 조회 API입니다.")
    @GetMapping("/{eventId}/prizes")
    public CommonResponse<PrizeEventResponseDTO.PrizeList> getPrizeList(
            @PathVariable final Long eventId) {
        return CommonResponse.onSuccess(prizeEventService.getPrizeList(eventId));
    }

    @PostMapping("/kakaolink/callback")
    public ResponseEntity<String> handleKakaoLinkCallback(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("X-Kakao-Resource-ID") String kakaoResourceId,
            @RequestHeader("User-Agent") String userAgent,
            @RequestBody KakaolinkCallbackRequestDTO request) {

        prizeEventService.issuePrizeTicket(request);
        return ResponseEntity.ok("Received");
    }
}
