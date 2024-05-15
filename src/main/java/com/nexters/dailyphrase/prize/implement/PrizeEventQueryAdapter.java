package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.PrizeEvent;
import com.nexters.dailyphrase.prize.domain.repository.PrizeEventRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeEventQueryAdapter {
    private final PrizeEventRepository prizeEventRepository;

    public PrizeEvent findById(final Long eventId) {
        return prizeEventRepository.findById(eventId).orElseThrow();
    }
}
