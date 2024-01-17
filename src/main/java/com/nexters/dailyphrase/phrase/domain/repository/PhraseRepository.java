package com.nexters.dailyphrase.phrase.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.phrase.domain.Phrase;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {}
