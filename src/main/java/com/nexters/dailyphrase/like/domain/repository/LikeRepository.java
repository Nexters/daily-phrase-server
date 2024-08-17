package com.nexters.dailyphrase.like.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMember_IdAndPhrase_Id(Long memberId, Long phraseId);

    List<Like> findByMember_Id(Long memberId);

    int countByPhrase_Id(Long id);

    @Modifying
    @Query("DELETE FROM Like l WHERE l.phrase.id = :phraseId")
    void deleteByPhraseId(Long phraseId);

    boolean existsByMember_IdAndPhrase_Id(Long memberId, Long phraseId);
}
