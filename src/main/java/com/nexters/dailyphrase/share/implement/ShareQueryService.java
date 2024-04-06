package com.nexters.dailyphrase.share.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.share.domain.repository.ShareRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShareQueryService {
    private final ShareRepository shareRepository;
}
