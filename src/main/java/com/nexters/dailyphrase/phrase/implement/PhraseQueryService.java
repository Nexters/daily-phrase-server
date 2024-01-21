package com.nexters.dailyphrase.phrase.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.exception.PhraseNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseQueryService {
    private final PhraseRepository phraseRepository;

    public Phrase findById(final Long id) {
        return phraseRepository.findById(id).orElseThrow(() -> PhraseNotFoundException.EXCEPTION);
    }
}
