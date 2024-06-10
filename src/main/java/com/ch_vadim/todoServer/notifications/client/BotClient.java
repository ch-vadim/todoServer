package com.ch_vadim.todoServer.notifications.client;

import com.ch_vadim.todoServer.config.BotConfig;
import com.ch_vadim.todoServer.notifications.dto.NotificationDtoBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BotClient {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;

    public void sendNotifications(List<NotificationDtoBot> notifications) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String notificationUrl = botConfig.url() + "/api/notifications";

        HttpEntity<List<NotificationDtoBot>> requestEntity = new HttpEntity<>(notifications, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                notificationUrl,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            log.warn("Error to send notifications to bot: " + response);
        }
    }
}
