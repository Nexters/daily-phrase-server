package com.nexters.dailyphrase.readhistory.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.readhistory.domain.ReadHistory;

public interface ReadHistoryRepository extends JpaRepository<ReadHistory, Long> {}
