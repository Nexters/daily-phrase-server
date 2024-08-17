package com.nexters.dailyphrase.phraseimage.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PhraseImageCommandAdapter {
    private final PhraseImageRepository phraseImageRepository;

    public void create(final PhraseImage phraseImage) {

        phraseImageRepository.save(phraseImage);
    }

    public void deleteByPhraseId(final Long id) {
        phraseImageRepository.deleteByPhraseId(id);
    }
}
