package com.nexters.dailyphrase.share.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.domain.repository.ShareRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ShareCommandAdapter {
    private final ShareRepository shareRepository;

    public Share add(Share share) {
        return shareRepository.save(share);
    }
}
