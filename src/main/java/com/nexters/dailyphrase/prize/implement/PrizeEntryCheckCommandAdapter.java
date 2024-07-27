package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.PrizeEntryCheck;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEntryCheckRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeEntryCheckCommandAdapter {
    private final PrizeEntryCheckRepository prizeEntryCheckRepository;

    public PrizeEntryCheck add(final PrizeEntryCheck prizeEntryCheck) {
        return prizeEntryCheckRepository.save(prizeEntryCheck);
    }
}
