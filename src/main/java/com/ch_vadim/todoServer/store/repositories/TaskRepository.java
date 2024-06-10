package com.ch_vadim.todoServer.store.repositories;

import com.ch_vadim.todoServer.store.entities.TaskEntity;
import com.ch_vadim.todoServer.store.entities.TaskState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("UPDATE TaskEntity t SET t.state = :state WHERE t.id = :id")
    TaskEntity updateStateById(long id, TaskState state);

    @Query("SELECT t FROM TaskEntity t WHERE t.user.chatId = :userId")
    List<TaskEntity> findByUserId(long userId);
}
