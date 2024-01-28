package com.nexters.dailyphrase.admin.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.implement.PhraseImageCommandService;
import lombok.RequiredArgsConstructor;
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
        final PhraseImage phraseImage = adminMapper.toPhraseImage(request);

        Phrase savedPhrase = phraseCommandService.create(phrase);
        phraseImage.setPhrase(savedPhrase);
        phraseImageCommandService.create(phraseImage);

        return adminMapper.toAddPhrase(savedPhrase);
    }

    @Transactional(readOnly = true)
    public AdminResponseDTO.AdminPhraseDetail getAdminPhraseDetail(final Long id) {
        Phrase phrase = phraseQueryService.findById(id);
        return adminMapper.toAdminPhraseDetail(phrase);
    }

    @Transactional
    public AdminResponseDTO.ModifyPhrase modifyPhrase(
            final Long id, final AdminRequestDTO.ModifyPhrase request) {

        final Phrase requestedPhrase = adminMapper.toPhrase(request);
        final PhraseImage requestedPhraseImage = adminMapper.toPhraseImage(request);

        Phrase updatedPhrase = phraseQueryService.findById(id);
        updatedPhrase.setTitle(requestedPhrase.getTitle());
        updatedPhrase.setContent(requestedPhrase.getContent());

        PhraseImage updatedPhraseImage = updatedPhrase.getPhraseImage();
        updatedPhraseImage.setImageRatio(requestedPhraseImage.getImageRatio());
        updatedPhraseImage.setFileName(requestedPhraseImage.getFileName());
        updatedPhrase.setPhraseImage(updatedPhraseImage);

        return adminMapper.toModifyPhrase(updatedPhrase);
    }

    @Transactional(readOnly = true)
    public AdminResponseDTO.AdminPhraseList getAdminPhraseList() {
        return phraseQueryService.findAdminPhraseListDTO();
    }

    @Transactional
    public AdminResponseDTO.DeletePhrase deletePhrase(final Long id) {

        Phrase phrase = phraseQueryService.findById(id);

        phraseCommandService.deleteById(id);
        phraseImageCommandService.deleteByPhraseId(id);

        return adminMapper.toDeletePhrase();
    }
}
