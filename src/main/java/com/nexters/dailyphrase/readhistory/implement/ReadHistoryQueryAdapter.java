package com.nexters.dailyphrase.readhistory.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.readhistory.domain.repository.ReadHistoryRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ReadHistoryQueryAdapter {
    private final ReadHistoryRepository readHistoryRepository;
}
