package com.nexters.dailyphrase.prize.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.prize.domain.Prize;

public interface PrizeRepository extends JpaRepository<Prize, Long> {}
