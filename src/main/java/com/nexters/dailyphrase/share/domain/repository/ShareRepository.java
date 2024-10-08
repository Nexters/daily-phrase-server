package com.nexters.dailyphrase.share.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.share.domain.Share;

public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAllByMemberIdAndPhraseId(Long memberId, Long phraseId);
}
