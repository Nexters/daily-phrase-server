package com.nexters.dailyphrase.admin.implement;

import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;
import com.nexters.dailyphrase.admin.exception.AdminNotFoundException;
import com.nexters.dailyphrase.common.annotation.Adapter;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class AdminQueryAdapter {
    private final AdminRepository adminRepository;

    public Admin findByLoginId(String userId) {
        return adminRepository
                .findByUserId(userId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}
