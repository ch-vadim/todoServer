package com.ch_vadim.todoServer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot")
public record BotConfig(
        String url
) {
}
