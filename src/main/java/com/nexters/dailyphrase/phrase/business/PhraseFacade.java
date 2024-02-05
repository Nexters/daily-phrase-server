package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.like.implement.LikeQueryService;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseFacade {
    private final PhraseQueryService phraseQueryService;
    private final PhraseCommandService phraseCommandService;
    private final LikeQueryService likeQueryService;
    private final PhraseMapper phraseMapper;

    @Transactional
    public PhraseResponseDTO.PhraseDetail getPhraseDetail(final Long id) {
        phraseCommandService.increaseViewCountById(id);
        Phrase phrase = phraseQueryService.findById(id);
        int likeCount = likeQueryService.countByPhraseId(id);
        return phraseMapper.toPhraseDetail(phrase, likeCount);
    }

    @Transactional(readOnly = true)
    public PhraseResponseDTO.PhraseList getPhraseList(final int page, final int size) {
        return phraseQueryService.findPhraseListDTO(page, size);
    }
}
