package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.phrase.business.PhraseMapper;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.admin.implement.AdminCommandService;
import com.nexters.dailyphrase.admin.implement.AdminQueryService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final AdminQueryService adminQueryService;
    private final AdminCommandService adminCommandService;


    // 관리자 글귀 등록 (단건)
    @Transactional
    public void addPhrase(final AdminRequestDTO.AddPhrase request) {


        final Phrase phrase = AdminMapper.DTOtoPhrase(request);
        final PhraseImage phraseImage= AdminMapper.DTOtoPhraseImage(request);
        phraseImage.setPhrase(phrase);

       adminCommandService.create(phrase,phraseImage);

    }



}
