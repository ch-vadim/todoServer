package com.ch_vadim.todoServer.notifications.service;

import com.ch_vadim.todoServer.notifications.client.BotClient;
import com.ch_vadim.todoServer.notifications.client.EmailSenderClient;
import com.ch_vadim.todoServer.notifications.dto.NotificationDtoBot;
import com.ch_vadim.todoServer.notifications.dto.NotificationDtoEmail;
import com.ch_vadim.todoServer.store.entities.NotificationState;
import com.ch_vadim.todoServer.store.entities.TaskEntity;
import com.ch_vadim.todoServer.store.entities.TaskState;
import com.ch_vadim.todoServer.store.entities.UserEntity;
import com.ch_vadim.todoServer.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final BotClient botClient;
    private final EmailSenderClient emailSenderClient;

    public List<UserEntity> getAllSubscribesUsersWithNotDoneTasks() {
        return userRepository.findAllSubscribesUsersWithNotDoneTasks(NotificationState.NOTHING, TaskState.COMPLETED);

    }


    @Transactional
    public void sendNotifications() {
        List<UserEntity> users = getAllSubscribesUsersWithNotDoneTasks();
        List<NotificationDtoBot> notificationsDtoBot = new ArrayList<>();
        List<NotificationDtoEmail> notificationsDtoEmail = new ArrayList<>();
        for (UserEntity user: users) {
            if (!user.getTasks().isEmpty()) {
                String message = buildNotificationMessage(user.getTasks());
                if (user.getNotificationState().equals(NotificationState.TG) ||
                        user.getNotificationState().equals(NotificationState.ALL)
                ) {
                    notificationsDtoBot.add(new NotificationDtoBot(user.getChatId(), message));
                }

                if (user.getNotificationState().equals(NotificationState.EMAIL) ||
                        user.getNotificationState().equals(NotificationState.ALL)
                ) {
                    notificationsDtoEmail.add(new NotificationDtoEmail(user.getEmail(), message));
                }
            }
        }

        if (!notificationsDtoBot.isEmpty()) botClient.sendNotifications(notificationsDtoBot);
        if (!notificationsDtoEmail.isEmpty()) emailSenderClient.sendNotifications(notificationsDtoEmail);
    }

    private String buildNotificationMessage(List<TaskEntity> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Today you planned to accomplish the following tasks: \n");
        for (TaskEntity task: tasks) {
            sb.append(task.getId())
                    .append(" ")
                    .append(task.getName())
                    .append("\n");
        }
        sb.append("Good luck");
        return sb.toString();
    }
}
