package com.nexters.dailyphrase.like.domain.repository;

import com.nexters.dailyphrase.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
