package com.nexters.dailyphrase.like.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.like.domain.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMember_IdAndPhrase_Id(Long memberId, Long phraseId);
}
