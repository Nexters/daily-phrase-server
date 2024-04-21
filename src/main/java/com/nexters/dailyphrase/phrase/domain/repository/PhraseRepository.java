package com.nexters.dailyphrase.phrase.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.phrase.domain.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long>, PhraseCustomRepository {

    @Query("select p from Phrase p left join fetch p.phraseImage where p.id = :phraseId")
    Optional<Phrase> findById(Long phraseId);

    @Query(
            "select p from Phrase p left join fetch p.phraseImage where p.id = :phraseId and p.isReserved = false")
    Optional<Phrase> findPublishPhraseById(Long phraseId);

    @Query(
            "select p from Phrase p where p.publishDate = :publishDate")
    List<Phrase> findPhraseByPublishDate(LocalDate publishDate);

    @Modifying
    @Query("update Phrase p set p.viewCount = p.viewCount + 1 where p.id = :phraseId")
    void updateViewCountById(Long phraseId);

    @Modifying
    @Query(
            "update Phrase p set p.isReserved = false where p.isReserved = true and p.publishDate = :publishDate")
    void updateByIsPublishDate(LocalDate publishDate);
}
