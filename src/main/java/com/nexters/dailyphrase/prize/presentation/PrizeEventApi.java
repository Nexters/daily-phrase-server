package com.nexters.dailyphrase.prize.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.consts.DailyPhraseStatic;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.common.validation.KakaoAppAdminKeyValidator;
import com.nexters.dailyphrase.prize.business.PrizeEventService;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventRequestDTO;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import com.nexters.dailyphrase.share.presentation.dto.KakaolinkCallbackRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "07-Event 🎁", description = "경품 응모 이벤트 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class PrizeEventApi {

    private final PrizeEventService prizeEventService;
    private final KakaoAppAdminKeyValidator kakaoAppAdminKeyValidator;

    @Operation(
            summary = "07-01 Event 🎁 경품 응모 이벤트의 이벤트 정보 조회 Made By 성훈",
            description = "경품 응모 이벤트의 이벤트 정보 조회 API입니다.")
    @GetMapping("/info")
    public CommonResponse<PrizeEventResponseDTO.PrizeEventInfo> getPrizeEventInfo() {
        final Long eventId = DailyPhraseStatic.CURRENT_ACTIVE_EVENT_ID;
        return CommonResponse.onSuccess(prizeEventService.getPrizeEventInfo(eventId));
    }

    @Operation(
            summary = "07-02 Event 🎁 경품 응모 이벤트의 경품 목록 조회 Made By 성훈",
            description = "경품 응모 이벤트의 경품 목록 조회 API입니다.")
    @GetMapping("/prizes")
    public CommonResponse<PrizeEventResponseDTO.PrizeList> getPrizeList() {
        final Long eventId = DailyPhraseStatic.CURRENT_ACTIVE_EVENT_ID;
        return CommonResponse.onSuccess(prizeEventService.getPrizeList(eventId));
    }

    @Operation(
            summary = "07-03 Event 🎁 경품 응모 이벤트의 경품 응모 Made By 성훈",
            description = "경품 응모 이벤트의 경품 응모 API입니다.")
    @PostMapping("/enter")
    public CommonResponse<PrizeEventResponseDTO.EnterPrizeEvent> enterPrize(
            @RequestBody PrizeEventRequestDTO.EnterPrizeEvent request) {
        return CommonResponse.onSuccess(prizeEventService.enterPrize(request));
    }

    @Operation(
            summary = "07-04 Event 🎁 경품 응모 이벤트의 경품 응모 결과 확인 Made By 성훈",
            description = "경품 응모 이벤트의 경품 응모 결과 확인 API입니다.")
    @GetMapping("/prizes/{prizeId}/entry-result")
    public CommonResponse<PrizeEventResponseDTO.PrizeEntryResult> getPrizeEntryResult(
            @PathVariable final Long prizeId) {
        return CommonResponse.onSuccess(prizeEventService.getPrizeEntryResult(prizeId));
    }

    @Operation(
            summary = "07-05 Event 🎁 경품 응모 이벤트의 당첨자 연락처 입력 Made By 성훈",
            description = "경품 응모 이벤트의 당첨자 연락처 입력 API입니다.")
    @PostMapping("/prizes/{prizeId}/phone-number")
    public CommonResponse<PrizeEventResponseDTO.EnterPhoneNumber> enterPhoneNumber(
            @PathVariable final Long prizeId,
            @RequestBody PrizeEventRequestDTO.EnterPhoneNumber request) {
        return CommonResponse.onSuccess(prizeEventService.enterPhoneNumber(prizeId, request));
    }

    @Operation(
            summary = "07-06 Event 🎁 경품 응모 이벤트의 응모권 발급용 카카오 콜백 Made By 성훈",
            description = "경품 응모 이벤트의 응모권 발급용 카카오 콜백입니다. (직접 호출 X)")
    @PostMapping("/kakaolink/callback")
    public ResponseEntity<String> handleKakaoLinkCallback(
            @RequestHeader("Authorization")
                    @Schema(
                            description = "KakaoAK ${APP_ADMIN_KEY} 형태의 인증 헤더 (from Kakao Server)",
                            example = "KakaoAK ${APP_ADMIN_KEY}")
                    String authorizationHeader,
            @RequestHeader("X-Kakao-Resource-ID")
                    @Schema(
                            description = "카카오톡 공유 알림(콜백)별 유니크 ID (from Kakao Server)",
                            example = "Rvy1c2dkzBAZ5hGD3rqYbxvr")
                    String kakaoResourceId,
            @RequestHeader("User-Agent")
                    @Schema(
                            description = "카카오에서 보낸 요청임을 알리기 위한 문자열 (from Kakao Server)",
                            example = "KakaoOpenAPI/1.0")
                    String userAgent,
            @RequestBody KakaolinkCallbackRequestDTO request) {
        if (kakaoAppAdminKeyValidator.isValid(authorizationHeader))
            prizeEventService.issuePrizeTicket(request);
        return ResponseEntity.ok("Received");
    }
}
