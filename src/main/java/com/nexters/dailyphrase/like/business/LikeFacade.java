package com.nexters.dailyphrase.like.business;

import com.nexters.dailyphrase.like.implement.LikeCommandService;
import com.nexters.dailyphrase.like.implement.LikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final LikeQueryService likeQueryService;
    private final LikeCommandService likeCommandService;
}
