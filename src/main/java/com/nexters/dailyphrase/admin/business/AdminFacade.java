package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.implement.PhraseImageCommandService;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final PhraseCommandService phraseCommandService;
    private final PhraseQueryService phraseQueryService;
    private final PhraseImageCommandService phraseImageCommandService;
    private final AdminMapper adminMapper;

    @Transactional
    public AdminResponseDTO.AddPhrase addPhrase(final AdminRequestDTO.AddPhrase request) {

        final Phrase phrase = adminMapper.toPhrase(request);
        final PhraseImage phraseImage= adminMapper.toPhraseImage(request);

        Phrase savedPhrase=phraseCommandService.create(phrase);
        phraseImage.setPhrase(savedPhrase);
        phraseImageCommandService.create(phraseImage);

        return adminMapper.toAddPhrase(savedPhrase);
    }

    @Transactional
    public AdminResponseDTO.AdminPhraseDetail getAdminPhraseDetail(final Long id) {
        Phrase phrase = phraseQueryService.findById(id);
        return adminMapper.toAdminPhraseDetail(phrase);
    }
}
