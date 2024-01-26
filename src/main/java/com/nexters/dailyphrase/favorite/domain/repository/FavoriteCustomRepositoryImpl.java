package com.nexters.dailyphrase.favorite.domain.repository;

import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
@RequiredArgsConstructor
public class FavoriteCustomRepositoryImpl implements FavoriteCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public FavoriteResponseDTO.FavoriteList findFavoriteListDTO(final Long memberId) {
        return null;
    }
}
