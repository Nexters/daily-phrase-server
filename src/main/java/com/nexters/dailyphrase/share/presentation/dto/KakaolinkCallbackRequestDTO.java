package com.nexters.dailyphrase.share.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class KakaolinkCallbackRequestDTO {
    // NOTE - https://developers.kakao.com/docs/latest/ko/message/callback#success-parameter
    @Schema(description = "카카오톡 공유 메시지가 전달된 채팅방의 타입 (from Kakao Server)")
    private String chatType;

    @Schema(description = "카카오톡 공유 메시지를 수신한 채팅방의 참고용 ID (from Kakao Server)")
    private String hashChatId;

    @Schema(description = "메시지 템플릿 ID를 사용해 카카오톡 공유 메시지를 보낸 경우 사용된 메시지 템플릿의 ID (from Kakao Server)")
    private Long templateId;

    @Schema(description = "매일 글귀 액세스 토큰 (from Android)")
    private String accessToken; // 자체 토큰
    //    private Map<String, Object> serverCallbackArgs;
}
