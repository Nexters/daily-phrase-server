package com.nexters.dailyphrase.prize.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.prize.domain.PrizeEntry;

public interface PrizeEntryRepository extends JpaRepository<PrizeEntry, Long> {
    boolean existsByMemberIdAndPrize_IdAndStatus(
            Long memberId, Long prizeId, PrizeEntryStatus status);

    List<PrizeEntry> findAllByMemberIdAndPrize_IdAndStatus(
            Long memberId, Long prizeId, PrizeEntryStatus status);
}
