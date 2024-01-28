package com.nexters.dailyphrase.phrase.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.phrase.business.PhraseFacade;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phrases")
public class PhraseApi {
    private final PhraseFacade phraseFacade;

    @GetMapping
    public CommonResponse<PhraseResponseDTO.PhraseList> getPhraseList(
            @RequestParam(required = false, defaultValue = "1") final int page,
            @RequestParam(required = false, defaultValue = "10") final int size) {
        return CommonResponse.onSuccess(phraseFacade.getPhraseList(page, size));
    }

    @GetMapping("/{id}")
    public CommonResponse<PhraseResponseDTO.PhraseDetail> getPhraseDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(phraseFacade.getPhraseDetail(id));
    }
}
