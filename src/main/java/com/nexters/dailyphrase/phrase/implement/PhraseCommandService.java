package com.nexters.dailyphrase.phrase.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseCommandService {
    private final PhraseRepository phraseRepository;
    private final PhraseQueryService phraseQueryService;

    public void increaseViewCountById(final Long phraseId) {
        phraseRepository.updateViewCountById(phraseId);
    }

    public Phrase create(final Phrase phrase) {

        return phraseRepository.save(phrase);
    }

    public void update(final Long id, Phrase phrase) {}
}
