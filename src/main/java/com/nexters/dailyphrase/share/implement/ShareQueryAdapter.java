package com.nexters.dailyphrase.share.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.share.domain.repository.ShareRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ShareQueryAdapter {
    private final ShareRepository shareRepository;
}
