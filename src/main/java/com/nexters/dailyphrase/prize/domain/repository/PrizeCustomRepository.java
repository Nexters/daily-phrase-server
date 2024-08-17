package com.nexters.dailyphrase.prize.domain.repository;

import com.nexters.dailyphrase.prize.presentation.dto.PrizeEventResponseDTO;

public interface PrizeCustomRepository {
    PrizeEventResponseDTO.PrizeList findPrizeListDTO(Long eventId);
}
