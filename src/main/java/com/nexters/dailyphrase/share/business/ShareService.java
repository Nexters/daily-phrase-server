package com.nexters.dailyphrase.share.business;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.utils.MemberUtils;
import com.nexters.dailyphrase.prize.implement.PrizeTicketQueryAdapter;
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
    private final PrizeTicketQueryAdapter prizeTicketQueryAdapter;
    private final MemberUtils memberUtils;
    private final ShareMapper shareMapper;

    @Transactional
    public ShareResponseDTO.AddShare addShare(final ShareRequestDTO.AddShare request) {
        Share share = shareMapper.toShare(request.getPhraseId(), request.getMemberId());
        Share savedShare = shareCommandAdapter.add(share);
        return shareMapper.toAddShare(savedShare);
    }

    @Transactional(readOnly = true)
    public ShareResponseDTO.MyShareCount getMyShareCount(final LocalDate date) {
        Long memberId = memberUtils.getCurrentMemberId();
        Integer shareCount =
                prizeTicketQueryAdapter.countPrizeTicketByMemberIdAndLocalDate(memberId, date);
        return shareMapper.toMyShareCount(shareCount, date);
    }
}
