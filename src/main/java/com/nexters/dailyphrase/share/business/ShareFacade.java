package com.nexters.dailyphrase.share.business;

import com.nexters.dailyphrase.share.implement.ShareCommandService;
import com.nexters.dailyphrase.share.implement.ShareQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShareFacade {
    private final ShareQueryService shareQueryService;
    private final ShareCommandService shareCommandService;
}
