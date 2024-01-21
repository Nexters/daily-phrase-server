package com.nexters.dailyphrase.phrase.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResponse<PhraseResponseDTO.PhraseList> getPhraseList() {
        return null;
    }

    @GetMapping("/{id}")
    public CommonResponse<PhraseResponseDTO.PhraseDetail> getPhraseDetail(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(phraseFacade.getPhraseDetail(id));
    }
}
