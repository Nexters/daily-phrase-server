package com.nexters.dailyphrase.phrase.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseCommandService {
    private final PhraseRepository phraseRepository;

    public void increaseViewCountById(Long phraseId) {
        phraseRepository.updateViewCountById(phraseId);
    }
}
