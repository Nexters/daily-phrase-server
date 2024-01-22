package com.nexters.dailyphrase.like.implement;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.like.domain.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeCommandService {
    private final LikeRepository likeRepository;

    public Like add(Like like) {
        return likeRepository.save(like);
    }
}
