package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AdminMapper {

    public Phrase toPhrase(AdminRequestDTO.AddPhrase request) {
        return Phrase.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
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
