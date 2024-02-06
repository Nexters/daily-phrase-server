package com.nexters.dailyphrase.favorite.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.favorite.exception.FavoriteNotFoundException;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

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

    public FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId) {
        return favoriteRepository.findFavoriteListDTO(memberId);
    }

    public List<Favorite> findByMemberId(Long id) {
        return favoriteRepository.findByMember_Id(id);
    }

    public boolean existsByMemberId(Long memberId) {
        return favoriteRepository.existsByMember_Id(memberId);
    }
}
