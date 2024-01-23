package com.nexters.dailyphrase.phraseimage.implement;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseImageCommandService {
    private final PhraseImageRepository phraseImageRepository;


    public void create(final Phrase savedPhrase, final PhraseImage phraseImage) {

        phraseImage.setPhrase(savedPhrase);
        phraseImageRepository.save(phraseImage);

    }
}
