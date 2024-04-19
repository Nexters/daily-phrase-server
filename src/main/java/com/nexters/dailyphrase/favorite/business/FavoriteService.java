package com.nexters.dailyphrase.favorite.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.implement.FavoriteCommandAdapter;
import com.nexters.dailyphrase.favorite.implement.FavoriteQueryAdapter;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteRequestDTO;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberQueryAdapter;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteQueryAdapter favoriteQueryAdapter;
    private final FavoriteCommandAdapter favoriteCommandAdapter;
    private final PhraseQueryAdapter phraseQueryAdapter;
    private final MemberQueryAdapter memberQueryAdapter;
    private final FavoriteMapper favoriteMapper;

    @Transactional
    public FavoriteResponseDTO.AddFavorite addFavorite(FavoriteRequestDTO.AddFavorite request) {
        Phrase phrase = phraseQueryAdapter.findById(request.getPhraseId());
        Member member = memberQueryAdapter.findById(request.getMemberId());
        Favorite favorite = favoriteMapper.toFavorite(phrase, member);
        Favorite savedFavorite = favoriteCommandAdapter.add(favorite);
        return favoriteMapper.toAddFavorite(savedFavorite);
    }

    @Transactional
    public FavoriteResponseDTO.RemoveFavorite removeLike(Long memberId, Long phraseId) {
        Favorite favorite = favoriteQueryAdapter.findByMemberIdAndPhraseId(memberId, phraseId);
        favoriteCommandAdapter.remove(favorite);
        return favoriteMapper.toRemoveFavorite(memberId, phraseId);
    }

    @Transactional(readOnly = true)
    public FavoriteResponseDTO.FavoriteList getFavoriteList(Long memberId) {
        return favoriteQueryAdapter.findFavoriteListDTO(memberId);
    }
}
