package com.nexters.dailyphrase.favorite.business;

import java.time.LocalDateTime;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.phrase.domain.Phrase;

@Mapper
public class FavoriteMapper {
    public Favorite toFavorite(Phrase phrase, Member member) {
        return Favorite.builder().phrase(phrase).member(member).build();
    }

    public FavoriteResponseDTO.AddFavorite toAddFavorite(Favorite savedFavorite) {
        return FavoriteResponseDTO.AddFavorite.builder()
                .memberId(savedFavorite.getMember().getId())
                .phraseId(savedFavorite.getPhrase().getId())
                .favoredAt(savedFavorite.getCreatedAt())
                .build();
    }

    public FavoriteResponseDTO.RemoveFavorite toRemoveFavorite(Long memberId, Long phraseId) {
        return FavoriteResponseDTO.RemoveFavorite.builder()
                .memberId(memberId)
                .phraseId(phraseId)
                .canceledAt(LocalDateTime.now())
                .build();
    }
}
