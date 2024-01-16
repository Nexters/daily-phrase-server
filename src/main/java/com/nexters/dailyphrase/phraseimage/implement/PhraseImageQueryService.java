package com.nexters.dailyphrase.phraseimage.implement;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhraseImageQueryService {
    private final PhraseRepository phraseRepository;
}
