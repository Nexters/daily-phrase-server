package com.nexters.dailyphrase.prize.business;

import com.nexters.dailyphrase.prize.implement.PrizeQueryAdapter;
import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrizeEventService {
    private final PrizeQueryAdapter prizeQueryAdapter;

    @Transactional(readOnly = true)
    public PrizeEventResponseDTO.PrizeList getPrizeList(final Long eventId) {
        return prizeQueryAdapter.findPrizeListDTO(eventId);
    }
}
