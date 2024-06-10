package com.ch_vadim.todoServer.notifications.client;

import com.ch_vadim.todoServer.notifications.dto.NotificationDtoBot;
import com.ch_vadim.todoServer.notifications.dto.NotificationDtoEmail;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailSenderClient {

    public void sendNotifications(List<NotificationDtoEmail> notifications) {

    }
}
