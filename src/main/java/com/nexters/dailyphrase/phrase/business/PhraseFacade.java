package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryService;
import com.nexters.dailyphrase.like.implement.LikeQueryService;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PhraseFacade {
    private final PhraseQueryService phraseQueryService;
    private final PhraseCommandService phraseCommandService;
    private final LikeQueryService likeQueryService;
    private final FavoriteQueryService favoriteQueryService;
    private final PhraseMapper phraseMapper;
    private final MemberUtils memberUtils;

    @Transactional
    public PhraseResponseDTO.PhraseDetail getPhraseDetail(final Long id) {
        phraseCommandService.increaseViewCountById(id);
        Phrase phrase = phraseQueryService.findPublishPhraseById(id);
        int likeCount = likeQueryService.countByPhraseId(id);
        Long memberId = memberUtils.getCurrentMemberId();
        boolean isLike = likeQueryService.existsByMemberIdAndPhraseId(memberId, id);
        boolean isFavorite = favoriteQueryService.existsByMemberIdAndPhraseId(memberId, id);
        return phraseMapper.toPhraseDetail(phrase, likeCount, isLike, isFavorite);
    }

    @Transactional(readOnly = true)
    public PhraseResponseDTO.PhraseList getPhraseList(final int page, final int size) {
        return phraseQueryService.findPhraseListDTO(page, size);
    }
}
