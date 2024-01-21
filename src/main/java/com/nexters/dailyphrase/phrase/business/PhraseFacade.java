package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private final PhraseMapper phraseMapper;

    @Transactional
    public PhraseResponseDTO.PhraseDetail getPhraseDetail(Long id) {
        phraseCommandService.increaseViewCountById(id);
        Phrase phrase = phraseQueryService.findById(id);
        return phraseMapper.toPhraseDetail(phrase);
    }
}
