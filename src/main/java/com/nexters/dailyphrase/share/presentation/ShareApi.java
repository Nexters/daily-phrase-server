package com.nexters.dailyphrase.share.presentation;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.share.business.ShareFacade;
import com.nexters.dailyphrase.share.presentation.dto.ShareRequestDTO;
import com.nexters.dailyphrase.share.presentation.dto.ShareResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shares")
@RequiredArgsConstructor
public class ShareApi {

    private final ShareFacade shareFacade;

    @PostMapping
    public CommonResponse<ShareResponseDTO.AddShare> addShare(
            @RequestBody final ShareRequestDTO.AddShare request) {
        return CommonResponse.onSuccess(shareFacade.addShare(request));
    }
}
