package com.nexters.dailyphrase.phraseimage.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PhraseImageQueryAdapter {
    private final PhraseRepository phraseRepository;
}
