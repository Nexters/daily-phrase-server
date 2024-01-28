package com.nexters.dailyphrase.phrase.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.annotation.ApiErrorCodeExample;
import com.nexters.dailyphrase.common.exception.GlobalErrorCode;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.phrase.business.PhraseFacade;
import com.nexters.dailyphrase.phrase.exception.PhraseErrorCode;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "02-Phrase📄", description = "글귀 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phrases")
public class PhraseApi {
    private final PhraseFacade phraseFacade;

    @Operation(summary = "02-01 Phrase📄 글귀 목록 조회 Made By 성훈", description = "글귀 목록 조회 API입니다.")
    @ApiErrorCodeExample(value = {GlobalErrorCode.class})
    @GetMapping
    public CommonResponse<PhraseResponseDTO.PhraseList> getPhraseList(
            @RequestParam(required = false, defaultValue = "1") final int page,
            @RequestParam(required = false, defaultValue = "10") final int size) {
        return CommonResponse.onSuccess(phraseFacade.getPhraseList(page, size));
    }

    @Operation(summary = "02-02 Phrase📄 글귀 상세 조회 Made By 성훈", description = "글귀 상세 조회 API입니다.")
    @ApiErrorCodeExample(value = {PhraseErrorCode.class, GlobalErrorCode.class})
    @GetMapping("/{id}")
    public CommonResponse<PhraseResponseDTO.PhraseDetail> getPhraseDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(phraseFacade.getPhraseDetail(id));
    }
}
