package com.nexters.dailyphrase.like.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.annotation.ApiErrorCodeExample;
import com.nexters.dailyphrase.common.exception.AuthErrorCode;
import com.nexters.dailyphrase.common.exception.GlobalErrorCode;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.like.business.LikeService;
import com.nexters.dailyphrase.like.exception.LikeErrorCode;
import com.nexters.dailyphrase.like.presentation.dto.LikeRequestDTO;
import com.nexters.dailyphrase.like.presentation.dto.LikeResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "03-Like👍", description = "좋아요 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeApi {
    private final LikeService likeService;

    @Operation(summary = "03-02 Like👍 글귀 좋아요 Made By 성훈", description = "글귀 좋아요 API입니다.")
    @ApiErrorCodeExample(value = {LikeErrorCode.class, GlobalErrorCode.class, AuthErrorCode.class})
    @PostMapping
    public CommonResponse<LikeResponseDTO.AddLike> addLike(
            @RequestBody final LikeRequestDTO.AddLike request) {
        return CommonResponse.onSuccess(likeService.addLike(request));
    }

    @Operation(summary = "03-01 Like👍 글귀 좋아요 취소 Made By 성훈", description = "글귀 좋아요 취소 API입니다.")
    @ApiErrorCodeExample(value = {LikeErrorCode.class, GlobalErrorCode.class, AuthErrorCode.class})
    @DeleteMapping("/members/{memberId}/phrases/{phraseId}")
    public CommonResponse<LikeResponseDTO.RemoveLike> removeLike(
            @PathVariable final Long memberId, @PathVariable final Long phraseId) {
        return CommonResponse.onSuccess(likeService.removeLike(memberId, phraseId));
    }
}
