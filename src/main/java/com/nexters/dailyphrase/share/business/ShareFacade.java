package com.nexters.dailyphrase.share.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.implement.ShareCommandService;
import com.nexters.dailyphrase.share.implement.ShareQueryService;
import com.nexters.dailyphrase.share.presentation.dto.ShareRequestDTO;
import com.nexters.dailyphrase.share.presentation.dto.ShareResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShareFacade {
    private final ShareQueryService shareQueryService;
    private final ShareCommandService shareCommandService;
    private final ShareMapper shareMapper;

    @Transactional
    public ShareResponseDTO.AddShare addShare(ShareRequestDTO.AddShare request) {
        Share share = shareMapper.toShare(request.getPhraseId(), request.getMemberId());
        Share savedShare = shareCommandService.add(share);
        return shareMapper.toAddShare(savedShare);
    }
}
