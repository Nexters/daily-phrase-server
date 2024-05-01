package com.nexters.dailyphrase.prize.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.prize.domain.PrizeEntry;

public interface PrizeEntryRepository extends JpaRepository<PrizeEntry, Long> {}
