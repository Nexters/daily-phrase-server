package com.nexters.dailyphrase.notification;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.nexters.dailyphrase.notification.business.NotificationMapper;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
public class SendNotification {

    @Value("${fcm.key.path}")
    private String SERVICE_ACCOUNT_JSON;

    @Value("${fcm.api.url}")
    private String FCM_API_URL;

    private final NotificationMapper notificationMapper;

    // 메시지 송신
    public void sendMessageTo(String body, String phraseId) throws IOException {

        final String title = "매일 글귀가 도착했어요☘";

        String message = notificationMapper.makeMessage(title, body, phraseId);
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody =
                RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request =
                new Request.Builder()
                        .url(FCM_API_URL)
                        .post(requestBody)
                        .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                        .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                        .build();

        Response response = client.newCall(request).execute();
    }

    // 인증
    private String getAccessToken() throws IOException {

        GoogleCredentials googleCredentials =
                GoogleCredentials.fromStream(
                                new ClassPathResource(SERVICE_ACCOUNT_JSON).getInputStream())
                        .createScoped(
                                Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
