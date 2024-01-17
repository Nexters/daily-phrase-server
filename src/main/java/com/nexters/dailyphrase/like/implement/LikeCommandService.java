package com.nexters.dailyphrase.like.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.like.domain.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeCommandService {
    private final LikeRepository likeRepository;
}
