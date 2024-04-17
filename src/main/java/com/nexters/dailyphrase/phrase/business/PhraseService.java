package com.nexters.dailyphrase.phrase.business;

import com.nexters.dailyphrase.favorite.implement.FavoriteQueryAdapter;
import com.nexters.dailyphrase.like.implement.LikeQueryAdapter;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandAdapter;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhraseService {
    private final PhraseQueryAdapter phraseQueryAdapter;
    private final PhraseCommandAdapter phraseCommandAdapter;
    private final LikeQueryAdapter likeQueryAdapter;
    private final FavoriteQueryAdapter favoriteQueryAdapter;
    private final PhraseMapper phraseMapper;
    private final MemberUtils memberUtils;

    @Transactional
    public PhraseResponseDTO.PhraseDetail getPhraseDetail(final Long id) {
        phraseCommandAdapter.increaseViewCountById(id);
        Phrase phrase = phraseQueryAdapter.findPublishPhraseById(id);
        int likeCount = likeQueryAdapter.countByPhraseId(id);
        Long memberId = memberUtils.getCurrentMemberId();
        boolean isLike = likeQueryAdapter.existsByMemberIdAndPhraseId(memberId, id);
        boolean isFavorite = favoriteQueryAdapter.existsByMemberIdAndPhraseId(memberId, id);
        return phraseMapper.toPhraseDetail(phrase, likeCount, isLike, isFavorite);
    }

    @Transactional(readOnly = true)
    public PhraseResponseDTO.PhraseList getPhraseList(final int page, final int size) {
        return phraseQueryAdapter.findPhraseListDTO(page, size);
    }
}
