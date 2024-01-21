package com.nexters.dailyphrase.phrase.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.phrase.domain.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {

    @Query("select p from Phrase p left join fetch p.phraseImage where p.id = :phraseId")
    Optional<Phrase> findById(Long phraseId);

    @Modifying
    @Query("update Phrase p set p.viewCount = p.viewCount + 1 where p.id = :phraseId")
    void updateViewCountById(Long phraseId);
}
