package com.nexters.dailyphrase.admin.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;
import com.nexters.dailyphrase.admin.exception.AdminNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final AdminRepository adminRepository;

    public Admin findByLoginId(String userId) {
        return adminRepository
                .findByUserId(userId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
