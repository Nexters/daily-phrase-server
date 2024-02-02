package com.nexters.dailyphrase.admin.implement;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;
import com.nexters.dailyphrase.admin.exception.AdminNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws AdminNotFoundException {
        return adminRepository
                .findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    private UserDetails createUserDetails(Admin admin) {
        return User.builder()
                .username(admin.getUserId())
                .password(passwordEncoder.encode(admin.getPassword()))
                .roles(String.valueOf(admin.getRole()))
                .build();
    }
}
