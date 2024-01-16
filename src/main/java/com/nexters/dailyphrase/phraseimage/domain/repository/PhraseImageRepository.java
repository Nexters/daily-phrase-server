package com.nexters.dailyphrase.phraseimage.domain.repository;

import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhraseImageRepository extends JpaRepository<PhraseImage, Long> {
}
