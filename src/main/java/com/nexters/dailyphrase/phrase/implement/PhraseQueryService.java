package com.nexters.dailyphrase.phrase.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseQueryService {
    private final PhraseRepository phraseRepository;
}
