package com.nexters.dailyphrase.like.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.like.business.LikeFacade;
import com.nexters.dailyphrase.like.presentation.dto.LikeRequestDTO;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeApi {
    private final LikeFacade likeFacade;

    @PostMapping
    public CommonResponse<LikeResponseDTO.AddLike> addLike(
            @RequestBody final LikeRequestDTO.AddLike request) {
        return CommonResponse.onSuccess(likeFacade.addLike(request));
    }

    @DeleteMapping("/members/{memberId}/phrases/{phraseId}")
    public CommonResponse<LikeResponseDTO.RemoveLike> removeLike(
            @PathVariable final Long memberId, @PathVariable final Long phraseId) {
        return CommonResponse.onSuccess(likeFacade.removeLike(memberId, phraseId));
    }
}
