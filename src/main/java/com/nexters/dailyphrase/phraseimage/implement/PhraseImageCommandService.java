package com.nexters.dailyphrase.phraseimage.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseImageCommandService {
    private final PhraseRepository phraseRepository;
}
