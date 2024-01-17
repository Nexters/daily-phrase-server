package com.nexters.dailyphrase.favorite.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.favorite.domain.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {}
