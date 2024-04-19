package com.nexters.dailyphrase.share.business;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.share.domain.Share;
import com.nexters.dailyphrase.share.presentation.dto.ShareResponseDTO;

@Mapper
public class ShareMapper {

    public Share toShare(Long phraseId, Long memberId) {
        return Share.builder().phraseId(phraseId).memberId(memberId).build();
    }

    public ShareResponseDTO.AddShare toAddShare(Share savedShare) {
        return ShareResponseDTO.AddShare.builder()
                .phraseId(savedShare.getPhraseId())
                .memberId(savedShare.getMemberId())
                .sharedAt(savedShare.getCreatedAt())
                .build();
    }
}
