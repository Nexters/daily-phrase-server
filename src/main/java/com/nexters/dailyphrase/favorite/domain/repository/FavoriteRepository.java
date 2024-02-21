package com.nexters.dailyphrase.favorite.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nexters.dailyphrase.favorite.domain.Favorite;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Long>, FavoriteCustomRepository {
    Optional<Favorite> findByMember_IdAndPhrase_Id(Long memberId, Long phraseId);

    List<Favorite> findByMember_Id(Long id);

    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.phrase.id = :phraseId")
    void deleteByPhraseId(Long phraseId);

    boolean existsByMember_IdAndPhrase_Id(Long memberId, Long phraseId);
}
