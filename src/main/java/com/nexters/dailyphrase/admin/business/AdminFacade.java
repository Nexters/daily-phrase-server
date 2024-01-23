package com.nexters.dailyphrase.admin.business;

import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.business.AdminMapper;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.member.presentation.dto.MemberRequestDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.implement.PhraseImageCommandService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final PhraseCommandService phraseCommandService;
    private final PhraseImageCommandService phraseImageCommandService;


    // 관리자 글귀 등록 (단건)
    @Transactional
    public AdminResponseDTO.AddPhrase addPhrase(final AdminRequestDTO.AddPhrase request) {


        final Phrase phrase = AdminMapper.toPhrase(request);
        final PhraseImage phraseImage= AdminMapper.toPhraseImage(request);
        phraseImage.setPhrase(phrase);

       Phrase savedPhrase=phraseCommandService.create(phrase);
       phraseImageCommandService.create(savedPhrase,phraseImage);

        return AdminMapper.toAddPhrase(savedPhrase);

    }


}
