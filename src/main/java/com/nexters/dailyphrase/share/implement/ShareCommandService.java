package com.nexters.dailyphrase.share.implement;

import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.domain.repository.ShareRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShareCommandService {
    private final ShareRepository shareRepository;

    public Share add(Share share) {
        return shareRepository.save(share);
    }
}
