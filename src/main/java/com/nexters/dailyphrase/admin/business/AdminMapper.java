package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

public class AdminMapper {

    public static Phrase DTOtoPhrase(AdminRequestDTO.AddPhrase request) {
        return Phrase.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public static PhraseImage DTOtoPhraseImage(AdminRequestDTO.AddPhrase request) {
        return PhraseImage.builder()
                .url(request.getImageUrl())
                .fileName(request.getFileName())
                .uuid(request.getUuid())
                .build();

    }


}
