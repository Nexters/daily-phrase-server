package com.nexters.dailyphrase.share.implement;

import com.nexters.dailyphrase.share.domain.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShareQueryService {
    private final ShareRepository shareRepository;


}
