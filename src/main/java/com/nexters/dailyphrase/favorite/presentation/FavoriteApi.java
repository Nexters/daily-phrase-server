package com.nexters.dailyphrase.favorite.presentation;

import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.common.annotation.ApiErrorCodeExample;
import com.nexters.dailyphrase.common.exception.AuthErrorCode;
import com.nexters.dailyphrase.common.exception.GlobalErrorCode;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.favorite.business.FavoriteService;
import com.nexters.dailyphrase.favorite.exception.FavoriteErrorCode;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteRequestDTO;
import com.nexters.dailyphrase.favorite.presentation.dto.FavoriteResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "04-Favorite⭐️", description = "즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorites")
public class FavoriteApi {
    private final FavoriteService favoriteService;

    @Operation(
            summary = "04-03 Favorite⭐ 글귀 즐겨찾기 저장 Made By 성훈",
            description = "글귀 즐겨찾기 저장 API입니다.")
    @ApiErrorCodeExample(
            value = {FavoriteErrorCode.class, GlobalErrorCode.class, AuthErrorCode.class})
    @PostMapping
    public CommonResponse<FavoriteResponseDTO.AddFavorite> addFavorite(
            @RequestBody final FavoriteRequestDTO.AddFavorite request) {
        return CommonResponse.onSuccess(favoriteService.addFavorite(request));
    }

    @Operation(
            summary = "04-02 Favorite⭐ 글귀 즐겨찾기 목록 조회 Made By 성훈",
            description = "글귀 즐겨찾기 목록 조회 API입니다.")
    @ApiErrorCodeExample(
            value = {FavoriteErrorCode.class, GlobalErrorCode.class, AuthErrorCode.class})
    @GetMapping("/members/{id}")
    public CommonResponse<FavoriteResponseDTO.FavoriteList> getFavoriteList(
            @PathVariable final Long id) {
        return CommonResponse.onSuccess(favoriteService.getFavoriteList(id));
    }

    @Operation(
            summary = "04-01 Favorite⭐ 글귀 즐겨찾기 취소 Made By 성훈",
            description = "글귀 즐겨찾기 취소 API입니다.")
    @ApiErrorCodeExample(
            value = {FavoriteErrorCode.class, GlobalErrorCode.class, AuthErrorCode.class})
    @DeleteMapping("/members/{memberId}/phrases/{phraseId}")
    public CommonResponse<FavoriteResponseDTO.RemoveFavorite> removeFavorite(
            @PathVariable final Long memberId, @PathVariable final Long phraseId) {
        return CommonResponse.onSuccess(favoriteService.removeLike(memberId, phraseId));
    }
}
