package com.nexters.dailyphrase.like.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.phrase.domain.Phrase;

@Component
public class LikeMapper {

    public Like toLike(Phrase phrase, Member member) {
        return Like.builder().phrase(phrase).member(member).build();
    }

    public LikeResponseDTO.AddLike toAddLike(Like savedLike) {
        return LikeResponseDTO.AddLike.builder()
                .memberId(savedLike.getMember().getId())
                .phraseId(savedLike.getPhrase().getId())
                .likedAt(savedLike.getCreatedAt())
                .build();
    }
}
