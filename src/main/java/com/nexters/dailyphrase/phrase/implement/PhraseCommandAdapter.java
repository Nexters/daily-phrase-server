package com.nexters.dailyphrase.phrase.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PhraseCommandAdapter {
    private final PhraseRepository phraseRepository;

    public void increaseViewCountById(final Long phraseId) {
        phraseRepository.updateViewCountById(phraseId);
    }

    public Phrase create(final Phrase phrase) {
        return phraseRepository.save(phrase);
    }

    public void deleteById(final Long id) {
        phraseRepository.deleteById(id);
    }
}
