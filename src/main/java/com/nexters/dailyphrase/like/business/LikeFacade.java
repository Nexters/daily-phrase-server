package com.nexters.dailyphrase.like.business;

import com.nexters.dailyphrase.like.presentation.dto.LikeRequestDTO;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.like.implement.LikeCommandService;
import com.nexters.dailyphrase.like.implement.LikeQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final LikeQueryService likeQueryService;
    private final LikeCommandService likeCommandService;

    public LikeResponseDTO.AddLike addLike(LikeRequestDTO.AddLike request) {
        return null;
    }
}
