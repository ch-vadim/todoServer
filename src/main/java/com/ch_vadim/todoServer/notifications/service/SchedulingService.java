package com.ch_vadim.todoServer.notifications.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final NotificationService notificationService;

    //every day 00:00
    @Scheduled(cron = "0 0 0 * * ?")
    public void startSendingNotifications() {
        notificationService.sendNotifications();
    }
}
