package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.PrizeEntry;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEntryRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeEntryCommandAdapter {
    private final PrizeEntryRepository prizeEntryRepository;

    public PrizeEntry add(PrizeEntry prizeEntry) {
        return prizeEntryRepository.save(prizeEntry);
    }
}
