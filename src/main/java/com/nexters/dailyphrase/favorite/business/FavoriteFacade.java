package com.nexters.dailyphrase.favorite.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.implement.FavoriteCommandService;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryService;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteRequestDTO;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberQueryService;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FavoriteFacade {
    private final FavoriteQueryService favoriteQueryService;
    private final FavoriteCommandService favoriteCommandService;
    private final PhraseQueryService phraseQueryService;
    private final MemberQueryService memberQueryService;
    private final FavoriteMapper favoriteMapper;

    @Transactional
    public FavoriteResponseDTO.AddFavorite addFavorite(FavoriteRequestDTO.AddFavorite request) {
        Phrase phrase = phraseQueryService.findById(request.getPhraseId());
        Member member = memberQueryService.findById(request.getMemberId());
        Favorite favorite = favoriteMapper.toFavorite(phrase, member);
        Favorite savedFavorite = favoriteCommandService.add(favorite);
        return favoriteMapper.toAddFavorite(savedFavorite);
    }

    @Transactional
    public FavoriteResponseDTO.RemoveFavorite removeLike(Long memberId, Long phraseId) {
        Favorite favorite = favoriteQueryService.findByMemberIdAndPhraseId(memberId, phraseId);
        favoriteCommandService.remove(favorite);
        return favoriteMapper.toRemoveFavorite(memberId, phraseId);
    }

    @Transactional(readOnly = true)
    public FavoriteResponseDTO.FavoriteList getFavoriteList(Long memberId) {
        return null;
    }
}
