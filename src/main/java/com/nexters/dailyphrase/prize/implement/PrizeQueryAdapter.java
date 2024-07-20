package com.nexters.dailyphrase.prize.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.prize.domain.Prize;
import com.nexters.dailyphrase.prize.domain.repository.PrizeRepository;
import com.nexters.dailyphrase.prize.exception.PrizeNotFoundException;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PrizeQueryAdapter {
    private final PrizeRepository prizeRepository;

    public PrizeEventResponseDTO.PrizeList findPrizeListDTO(final Long eventId) {
        return prizeRepository.findPrizeListDTO(eventId);
    }

    public Prize findById(Long prizeId) {
        return prizeRepository
                .findById(prizeId)
                .orElseThrow(() -> PrizeNotFoundException.EXCEPTION);
    }
}
