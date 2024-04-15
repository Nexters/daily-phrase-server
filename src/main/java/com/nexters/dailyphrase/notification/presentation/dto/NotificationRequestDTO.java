package com.nexters.dailyphrase.notification.presentation.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationRequestDTO {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class FcmMessage {

        private boolean validateOnly;
        private Message message;

        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class Message {
            private Notification notification;
            // private String      token;
            private String topic;
            private Data data;
        }

        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class Notification {
            private String title;
            private String body;
        }

        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        public static class Data {
            private String phraseId; // Long 불가
        }
    }
}
