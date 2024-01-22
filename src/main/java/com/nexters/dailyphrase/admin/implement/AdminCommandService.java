package com.nexters.dailyphrase.admin.implement;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.domain.repository.PhraseImageRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AdminCommandService {
    private final PhraseRepository phraseRepository;
    private final PhraseImageRepository phraseImageRepository;


    public void create(final Phrase phrase, final PhraseImage phraseImage) {

        Phrase savedPhrase = phraseRepository.save(phrase);
        phraseImage.setPhrase(savedPhrase);
        phraseImageRepository.save(phraseImage);

    }
}





