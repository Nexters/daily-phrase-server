package com.nexters.dailyphrase.like.business;

import java.time.LocalDateTime;

import com.nexters.dailyphrase.common.annotation.Mapper;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.phrase.domain.Phrase;

@Mapper
public class LikeMapper {

    public Like toLike(Phrase phrase, Member member) {
        return Like.builder().phrase(phrase).member(member).build();
    }

    public LikeResponseDTO.AddLike toAddLike(Like savedLike, int likeCount) {
        return LikeResponseDTO.AddLike.builder()
                .memberId(savedLike.getMember().getId())
                .phraseId(savedLike.getPhrase().getId())
                .likeCount(likeCount)
                .likedAt(savedLike.getCreatedAt())
                .build();
    }

    public LikeResponseDTO.RemoveLike toRemoveLike(Long memberId, Long phraseId, int likeCount) {
        return LikeResponseDTO.RemoveLike.builder()
                .memberId(memberId)
                .phraseId(phraseId)
                .likeCount(likeCount)
                .canceledAt(LocalDateTime.now())
                .build();
    }
}
