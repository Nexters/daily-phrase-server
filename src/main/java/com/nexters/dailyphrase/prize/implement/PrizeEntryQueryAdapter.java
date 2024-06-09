package com.nexters.dailyphrase.prize.implement;

import java.util.List;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import com.nexters.dailyphrase.prize.domain.PrizeEntry;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEntryRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeEntryQueryAdapter {
    private final PrizeEntryRepository prizeEntryRepository;

    public PrizeEntryStatus findWinningEntry(final Long memberId, final Long prizeId) {
        boolean isWinner =
                prizeEntryRepository.existsByMemberIdAndPrize_IdAndStatus(
                        memberId, prizeId, PrizeEntryStatus.WINNING);
        if (isWinner) return PrizeEntryStatus.WINNING;
        return PrizeEntryStatus.MISSED;
    }

    public List<PrizeEntry> findWinningEntryList(
            Long memberId, Long prizeId, PrizeEntryStatus status) {
        return prizeEntryRepository.findAllByMemberIdAndPrize_IdAndStatus(
                memberId, prizeId, status);
    }
}
