package com.nexters.dailyphrase.admin.implement;

import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCommandService {
    private final AdminRepository adminRepository;
}
