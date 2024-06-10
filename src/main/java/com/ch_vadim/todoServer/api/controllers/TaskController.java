package com.ch_vadim.todoServer.api.controllers;

import com.ch_vadim.todoServer.api.dto.TaskDto;
import com.ch_vadim.todoServer.api.exeptions.UserNotFoundException;
import com.ch_vadim.todoServer.service.TaskService;
import com.ch_vadim.todoServer.store.entities.TaskEntity;
import com.ch_vadim.todoServer.store.entities.TaskState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskDto task) {
        try {
            taskService.createTask(task);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{taskId}")
    public TaskDto completeTask(@PathVariable long taskId) {
        TaskEntity entity = taskService.competeTask(taskId);
        return TaskDto.builder()
                .id(entity.getId())
                .chatId(entity.getUser().getChatId())
                .name(entity.getName())
                .state(entity.getState())
                .build();
    }

    @GetMapping("/{userId}")
    public List<TaskDto> getAllTaskForUser(
            @PathVariable long userId,
            @RequestParam(required = false) boolean onlyActual
    ) {
        List<TaskEntity> entities = taskService.getAllTasksForUser(userId);
        return entities.stream()
                .filter(taskEntity ->
                    !(onlyActual && !taskEntity.getState().equals(TaskState.CREATED))
                )
                .sorted(Comparator.comparing(TaskEntity::getCreatedAt))
                .map(entity ->
                     TaskDto.builder()
                            .chatId(entity.getUser().getChatId())
                            .id(entity.getId())
                            .name(entity.getName())
                            .state(entity.getState())
                            .deadline(entity.getDeadline())
                             .build())
                .toList();
    }
}
