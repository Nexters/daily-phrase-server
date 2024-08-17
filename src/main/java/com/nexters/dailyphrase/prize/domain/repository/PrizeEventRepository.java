package com.nexters.dailyphrase.prize.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.prize.domain.PrizeEvent;

public interface PrizeEventRepository extends JpaRepository<PrizeEvent, Long> {}
