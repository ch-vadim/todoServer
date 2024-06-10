package com.ch_vadim.todoServer;

import com.ch_vadim.todoServer.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(BotConfig.class)
public class TodoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoServerApplication.class, args);
	}

}
