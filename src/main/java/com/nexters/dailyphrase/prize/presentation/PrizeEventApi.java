package com.nexters.dailyphrase.prize.presentation;

import com.nexters.dailyphrase.prize.business.PrizeEventService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;

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
}
