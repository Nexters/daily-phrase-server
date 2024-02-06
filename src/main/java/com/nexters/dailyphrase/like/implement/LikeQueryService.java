package com.nexters.dailyphrase.like.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.like.exception.LikeNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeQueryService {
    private final LikeRepository likeRepository;

    public Like findByMemberIdAndPhraseId(Long memberId, Long phraseId) {
        return likeRepository
                .findByMember_IdAndPhrase_Id(memberId, phraseId)
                .orElseThrow(() -> LikeNotFoundException.EXCEPTION);
    }

    public List<Like> findByMemberId(Long id) {
        return likeRepository.findByMember_Id(id);
    }

    public int countByPhraseId(Long id) {
        return likeRepository.countByPhrase_Id(id);
    }

    public boolean existsByMemberIdAndPhraseId(Long memberId, Long phraseId) {
        return likeRepository.existsByMember_IdAndPhrase_Id(memberId, phraseId);
    }
}
