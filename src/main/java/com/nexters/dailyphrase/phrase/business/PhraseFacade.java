package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseFacade {
    private final PhraseQueryService phraseQueryService;
    private final PhraseCommandService phraseCommandService;
}
