package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.repository.PrizeRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeQueryAdapter {
    private final PrizeRepository prizeRepository;
}
