package com.nexters.dailyphrase.phrase.implement;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhraseCommandService {
    private final PhraseRepository phraseRepository;
}
