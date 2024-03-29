package com.nexters.dailyphrase.phrase.domain.repository;

import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

public interface PhraseCustomRepository {
    PhraseResponseDTO.PhraseList findPhraseListDTO(int page, int size);

    AdminResponseDTO.AdminPhraseList findAdminPhraseListDTO();
}
