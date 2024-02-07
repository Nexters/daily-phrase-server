package com.nexters.dailyphrase.like.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.implement.LikeCommandService;
import com.nexters.dailyphrase.like.implement.LikeQueryService;
import com.nexters.dailyphrase.like.presentation.dto.LikeRequestDTO;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberQueryService;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final LikeQueryService likeQueryService;
    private final LikeCommandService likeCommandService;
    private final PhraseQueryService phraseQueryService;
    private final MemberQueryService memberQueryService;
    private final LikeMapper likeMapper;

    @Transactional
    public LikeResponseDTO.AddLike addLike(LikeRequestDTO.AddLike request) {
        Phrase phrase = phraseQueryService.findById(request.getPhraseId());
        Member member = memberQueryService.findById(request.getMemberId());
        Like like = likeMapper.toLike(phrase, member);
        Like savedLike = likeCommandService.add(like);
        int likeCount = likeQueryService.countByPhraseId(phrase.getId());
        return likeMapper.toAddLike(savedLike, likeCount);
    }

    @Transactional
    public LikeResponseDTO.RemoveLike removeLike(Long memberId, Long phraseId) {
        Like like = likeQueryService.findByMemberIdAndPhraseId(memberId, phraseId);
        likeCommandService.remove(like);
        int likeCount = likeQueryService.countByPhraseId(phraseId);
        return likeMapper.toRemoveLike(memberId, phraseId, likeCount);
    }
}
