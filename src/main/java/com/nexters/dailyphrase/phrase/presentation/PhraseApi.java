package com.nexters.dailyphrase.phrase.presentation;

import com.nexters.dailyphrase.phrase.business.PhraseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhraseApi {
    private final PhraseFacade phraseFacade;
}
