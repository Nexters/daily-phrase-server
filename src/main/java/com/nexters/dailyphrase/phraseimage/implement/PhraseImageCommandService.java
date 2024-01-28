package com.nexters.dailyphrase.phraseimage.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseImageCommandService {
    private final PhraseImageRepository phraseImageRepository;

    public void create(final PhraseImage phraseImage) {

        phraseImageRepository.save(phraseImage);
    }

    public void deleteByPhraseId(final Long id) {
        phraseImageRepository.deleteByPhraseId(id);
    }
}
