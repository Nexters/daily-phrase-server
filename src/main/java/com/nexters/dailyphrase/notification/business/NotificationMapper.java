package com.nexters.dailyphrase.notification.business;

import com.nexters.dailyphrase.common.annotation.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexters.dailyphrase.notification.presentation.dto.NotificationRequestDTO;

import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class NotificationMapper {

    @Value("${fcm.topic}")
    private String topic;

    private final ObjectMapper objectMapper;

    public String makeMessage(String title, String body, String phraseId)
            throws JsonProcessingException {
        NotificationRequestDTO.FcmMessage fcmMessage =
                NotificationRequestDTO.FcmMessage.builder()
                        .message(
                                NotificationRequestDTO.FcmMessage.Message.builder()
                                        .topic(topic)
                                        .notification(
                                                NotificationRequestDTO.FcmMessage.Notification
                                                        .builder()
                                                        .title(title)
                                                        .body(body)
                                                        .build())
                                        .data(
                                                NotificationRequestDTO.FcmMessage.Data.builder()
                                                        .phraseId(phraseId)
                                                        .build())
                                        .build())
                        .validateOnly(false)
                        .build();
        return objectMapper.writeValueAsString(fcmMessage);
    }
}
