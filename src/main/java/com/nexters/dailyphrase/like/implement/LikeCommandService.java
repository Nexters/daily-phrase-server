package com.nexters.dailyphrase.like.implement;

import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeCommandService {
    private final LikeRepository likeRepository;
}
