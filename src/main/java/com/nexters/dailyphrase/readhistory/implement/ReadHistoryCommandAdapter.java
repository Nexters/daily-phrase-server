package com.nexters.dailyphrase.readhistory.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.readhistory.domain.ReadHistory;
import com.nexters.dailyphrase.readhistory.domain.repository.ReadHistoryRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ReadHistoryCommandAdapter {
    private final ReadHistoryRepository readHistoryRepository;

    public void save(final ReadHistory readHistory) {
        readHistoryRepository.save(readHistory);
    }
}
