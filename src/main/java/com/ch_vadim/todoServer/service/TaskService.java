package com.ch_vadim.todoServer.service;

import com.ch_vadim.todoServer.api.dto.TaskDto;
import com.ch_vadim.todoServer.api.exeptions.UserNotFoundException;
import com.ch_vadim.todoServer.store.entities.TaskEntity;
import com.ch_vadim.todoServer.store.entities.TaskState;
import com.ch_vadim.todoServer.store.entities.UserEntity;
import com.ch_vadim.todoServer.store.repositories.TaskRepository;
import com.ch_vadim.todoServer.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public TaskEntity createTask(TaskDto task) {
        Optional<UserEntity> user = userRepository.findById(task.getChatId());
        if (user.isEmpty())
            throw new UserNotFoundException(
                    "User with chatId " + task.getChatId() + "not found"
            );

        TaskEntity entity = TaskEntity.builder()
                .name(task.getName())
                .user(user.get())
                .deadline(task.getDeadline())
                .build();
        return taskRepository.saveAndFlush(entity);
    }

    @Transactional
    public TaskEntity competeTask(long taskId) {
        return taskRepository.updateStateById(taskId, TaskState.COMPLETED);
    }

    @Transactional
    public List<TaskEntity> getAllTasksForUser(long userId) {
        return taskRepository.findByUserId(userId);
    }
}
