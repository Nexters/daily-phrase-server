package com.nexters.dailyphrase.phraseimage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

public interface PhraseImageRepository extends JpaRepository<PhraseImage, Long> {

    @Modifying
    @Query("DELETE FROM PhraseImage p WHERE p.phrase.id = :phraseId")
    void deleteByPhraseId(Long phraseId);
}
