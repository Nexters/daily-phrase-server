package com.nexters.dailyphrase.favorite.implement;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.favorite.domain.Favorite;
import com.nexters.dailyphrase.favorite.domain.repository.FavoriteRepository;
import com.nexters.dailyphrase.favorite.exception.DuplicateFavoriteException;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class FavoriteCommandAdapter {
    private final FavoriteRepository favoriteRepository;

    public Favorite add(Favorite favorite) {
        try {
            return favoriteRepository.save(favorite);
        } catch (DataIntegrityViolationException exception) {
            throw DuplicateFavoriteException.EXCEPTION;
        }
    }

    public void remove(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public void deleteByPhraseId(final Long id) {
        favoriteRepository.deleteByPhraseId(id);
    }

    public void deleteAllByIdInBatch(List<Long> favoriteIds) {
        favoriteRepository.deleteAllByIdInBatch(favoriteIds);
    }
}
