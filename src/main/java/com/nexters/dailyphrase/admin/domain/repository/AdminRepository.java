package com.nexters.dailyphrase.admin.domain.repository;

import com.nexters.dailyphrase.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
