package com.nexters.dailyphrase.like.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.like.implement.LikeCommandService;
import com.nexters.dailyphrase.like.implement.LikeQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final LikeQueryService likeQueryService;
    private final LikeCommandService likeCommandService;
}
