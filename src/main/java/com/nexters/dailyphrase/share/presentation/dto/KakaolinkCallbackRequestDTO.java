package com.nexters.dailyphrase.share.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class KakaolinkCallbackRequestDTO {
    // NOTE - https://developers.kakao.com/docs/latest/ko/message/callback#success-parameter
    private String chatType;
    private String hashChatId;
    private Long templateId;
    private String accessToken; // 자체 토큰
    //    private Map<String, Object> serverCallbackArgs;
}
