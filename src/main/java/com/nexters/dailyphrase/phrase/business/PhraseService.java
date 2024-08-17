package com.nexters.dailyphrase.phrase.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryAdapter;
import com.nexters.dailyphrase.like.implement.LikeQueryAdapter;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryAdapter;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseService {

    private final PhraseQueryAdapter phraseQueryAdapter;
    private final LikeQueryAdapter likeQueryAdapter;
    private final FavoriteQueryAdapter favoriteQueryAdapter;
    private final PhraseMapper phraseMapper;
    private final PhraseReadActionProcessor processReadAction;
    private final MemberUtils memberUtils;

    @Transactional(readOnly = true)
    public PhraseResponseDTO.PhraseDetail getPhraseDetail(final Long id, final String userAgent) {
        Long memberId = memberUtils.getCurrentMemberId();
        processReadAction.processReadAction(id, memberId, userAgent);
        Phrase phrase = phraseQueryAdapter.findPublishPhraseById(id);
        int likeCount = likeQueryAdapter.countByPhraseId(id);
        boolean isLike = likeQueryAdapter.existsByMemberIdAndPhraseId(memberId, id);
        boolean isFavorite = favoriteQueryAdapter.existsByMemberIdAndPhraseId(memberId, id);
        return phraseMapper.toPhraseDetail(phrase, likeCount, isLike, isFavorite);
    }

    @Transactional(readOnly = true)
    public PhraseResponseDTO.PhraseList getPhraseList(final int page, final int size) {
        return phraseQueryAdapter.findPhraseListDTO(page, size);
    }
}
