package com.nexters.dailyphrase.share.domain.repository;

import com.nexters.dailyphrase.share.domain.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {
}
