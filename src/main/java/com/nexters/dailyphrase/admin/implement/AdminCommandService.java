package com.nexters.dailyphrase.admin.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCommandService {
    private final AdminRepository adminRepository;
}
