package com.nexters.dailyphrase.favorite.implement;

import java.util.List;

import com.nexters.dailyphrase.common.annotation.Adapter;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.favorite.exception.FavoriteNotFoundException;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class FavoriteQueryAdapter {
    private final FavoriteRepository favoriteRepository;

    public Favorite findByMemberIdAndPhraseId(final Long memberId, final Long phraseId) {
        return favoriteRepository
                .findByMember_IdAndPhrase_Id(memberId, phraseId)
                .orElseThrow(() -> FavoriteNotFoundException.EXCEPTION);
    }

    public FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId) {
        return favoriteRepository.findFavoriteListDTO(memberId);
    }

    public List<Favorite> findByMemberId(Long id) {
        return favoriteRepository.findByMember_Id(id);
    }

    public boolean existsByMemberIdAndPhraseId(Long memberId, Long phraseId) {
        return favoriteRepository.existsByMember_IdAndPhrase_Id(memberId, phraseId);
    }
}
