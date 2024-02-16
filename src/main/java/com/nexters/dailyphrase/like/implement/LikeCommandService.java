package com.nexters.dailyphrase.like.implement;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.like.domain.Like;
import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.like.exception.DuplicateLikeException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeCommandService {
    private final LikeRepository likeRepository;

    public Like add(Like like) {
        try {
            return likeRepository.save(like);
        } catch (DataIntegrityViolationException exception) {
            throw DuplicateLikeException.EXCEPTION;
        }
    }

    public void remove(Like like) {
        likeRepository.delete(like);
    }

    public void deleteByPhraseId(final Long id) {
        likeRepository.deleteByPhraseId(id);
    }

    public void deleteAllByIdInBatch(List<Long> likeIds) {
        likeRepository.deleteAllByIdInBatch(likeIds);
    }
}
