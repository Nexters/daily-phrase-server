package com.nexters.dailyphrase.phrase.domain.repository;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {
}
