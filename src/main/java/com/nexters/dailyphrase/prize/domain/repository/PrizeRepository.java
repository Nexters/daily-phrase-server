package com.nexters.dailyphrase.prize.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.prize.domain.Prize;

public interface PrizeRepository extends JpaRepository<Prize, Long>, PrizeCustomRepository {
    @Query("SELECT p.id FROM Prize p")
    List<Long> findAllIds();
}
