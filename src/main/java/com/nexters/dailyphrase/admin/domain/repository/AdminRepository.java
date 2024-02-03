package com.nexters.dailyphrase.admin.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.admin.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserId(String userId);
}
