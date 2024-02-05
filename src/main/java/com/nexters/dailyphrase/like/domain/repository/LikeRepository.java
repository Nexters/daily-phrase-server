package com.nexters.dailyphrase.like.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMember_IdAndPhrase_Id(Long memberId, Long phraseId);

    List<Like> findByMember_Id(Long memberId);

    int countByPhrase_Id(Long id);
}
