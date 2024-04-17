package com.nexters.dailyphrase.share.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.implement.ShareCommandAdapter;
import com.nexters.dailyphrase.share.implement.ShareQueryAdapter;
import com.nexters.dailyphrase.share.presentation.dto.ShareRequestDTO;
import com.nexters.dailyphrase.share.presentation.dto.ShareResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final ShareQueryAdapter shareQueryAdapter;
    private final ShareCommandAdapter shareCommandAdapter;
    private final ShareMapper shareMapper;

    @Transactional
    public ShareResponseDTO.AddShare addShare(ShareRequestDTO.AddShare request) {
        Share share = shareMapper.toShare(request.getPhraseId(), request.getMemberId());
        Share savedShare = shareCommandAdapter.add(share);
        return shareMapper.toAddShare(savedShare);
    }
}
