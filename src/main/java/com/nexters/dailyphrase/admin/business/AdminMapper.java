package com.nexters.dailyphrase.admin.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

@Component
public class AdminMapper {

    public Phrase toPhrase(AdminRequestDTO.AddPhrase request) {
        return Phrase.builder().title(request.getTitle()).content(request.getContent()).build();
    }

    public PhraseImage toPhraseImage(AdminRequestDTO.AddPhrase request) {
        return PhraseImage.builder()
                .fileName(request.getFileName())
                .imageRatio(request.getImageRatio())
                .build();
    }

    public AdminResponseDTO.AddPhrase toAddPhrase(Phrase savedPhrase) {
        return AdminResponseDTO.AddPhrase.builder()
                .id(savedPhrase.getId())
                .createdAt(savedPhrase.getCreatedAt())
                .build();
    }
}
