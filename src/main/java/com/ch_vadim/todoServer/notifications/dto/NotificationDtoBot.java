package com.ch_vadim.todoServer.notifications.dto;

public record NotificationDtoBot (
    long chatId,
    String message
){}
