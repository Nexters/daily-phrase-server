package com.nexters.dailyphrase.phrase.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.exception.PhraseNotFoundException;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseQueryService {
    private final PhraseRepository phraseRepository;

    public Phrase findById(final Long id) {
        return phraseRepository.findById(id).orElseThrow(() -> PhraseNotFoundException.EXCEPTION);
    }

    public Phrase findPublishPhraseById(final Long id) {
        return phraseRepository
                .findPublishPhraseById(id)
                .orElseThrow(() -> PhraseNotFoundException.EXCEPTION);
    }

    public PhraseResponseDTO.PhraseList findPhraseListDTO(final int page, final int size) {
        return phraseRepository.findPhraseListDTO(page, size);
    }

    public AdminResponseDTO.AdminPhraseList findAdminPhraseListDTO() {
        return phraseRepository.findAdminPhraseListDTO();
    }
}
