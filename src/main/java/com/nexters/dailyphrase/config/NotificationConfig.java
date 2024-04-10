package com.nexters.dailyphrase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class NotificationConfig {

    @Value("${project.properties.firebase-create-scoped}")
    String fireBaseCreateScoped;

    @Value("${project.properties.firebase-topic}")
    String topic;

    private FirebaseMessaging instance;

    @PostConstruct
    public void firebaseSetting() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("firebase/daily-phrase-android-firebase-adminsdk-letn6-99995c786c.json").getInputStream())
                .createScoped((Arrays.asList(fireBaseCreateScoped)));
        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
        this.instance = FirebaseMessaging.getInstance(app);
    }


    public void pushAlarm(AdminResponseDTO.UploadNotification data) throws FirebaseMessagingException {
        Message message = getMessage(data);
        sendMessage(message);
    }

    private Message getMessage(AdminResponseDTO.UploadNotification data) {
        Notification notification
                = Notification.builder()
                .setTitle(data.getTitle())
                .setBody(data.getBody())
                .build();
        Message.Builder builder = Message.builder();
        return builder
                .setTopic(topic)
                .setNotification(notification)
                .build();
    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        return this.instance.send(message);
    }
}