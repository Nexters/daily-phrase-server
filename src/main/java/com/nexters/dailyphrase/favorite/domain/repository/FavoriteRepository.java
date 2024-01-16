package com.nexters.dailyphrase.favorite.domain.repository;

import com.nexters.dailyphrase.favorite.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
