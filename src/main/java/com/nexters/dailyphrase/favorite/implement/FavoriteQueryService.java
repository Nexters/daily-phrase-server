package com.nexters.dailyphrase.favorite.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.favorite.exception.FavoriteNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteQueryService {
    private final FavoriteRepository favoriteRepository;

    public Favorite findByMemberIdAndPhraseId(final Long memberId, final Long phraseId) {
        return favoriteRepository
                .findByMember_IdAndPhrase_Id(memberId, phraseId)
                .orElseThrow(() -> FavoriteNotFoundException.EXCEPTION);
    }
}
