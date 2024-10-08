package com.nexters.dailyphrase.like.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.implement.LikeCommandAdapter;
import com.nexters.dailyphrase.like.implement.LikeQueryAdapter;
import com.nexters.dailyphrase.like.presentation.dto.LikeRequestDTO;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.member.implement.MemberQueryAdapter;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeQueryAdapter likeQueryAdapter;
    private final LikeCommandAdapter likeCommandAdapter;
    private final PhraseQueryAdapter phraseQueryAdapter;
    private final MemberQueryAdapter memberQueryAdapter;
    private final LikeMapper likeMapper;

    @Transactional
    public LikeResponseDTO.AddLike addLike(LikeRequestDTO.AddLike request) {
        Phrase phrase = phraseQueryAdapter.findById(request.getPhraseId());
        Member member = memberQueryAdapter.findById(request.getMemberId());
        Like like = likeMapper.toLike(phrase, member);
        Like savedLike = likeCommandAdapter.add(like);
        int likeCount = likeQueryAdapter.countByPhraseId(phrase.getId());
        return likeMapper.toAddLike(savedLike, likeCount);
    }

    @Transactional
    public LikeResponseDTO.RemoveLike removeLike(Long memberId, Long phraseId) {
        Like like = likeQueryAdapter.findByMemberIdAndPhraseId(memberId, phraseId);
        likeCommandAdapter.remove(like);
        int likeCount = likeQueryAdapter.countByPhraseId(phraseId);
        return likeMapper.toRemoveLike(memberId, phraseId, likeCount);
    }
}
