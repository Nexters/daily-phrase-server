package com.nexters.dailyphrase.phrase.business;

import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhraseFacade {
    private final PhraseQueryService phraseQueryService;
    private final PhraseCommandService phraseCommandService;
}
