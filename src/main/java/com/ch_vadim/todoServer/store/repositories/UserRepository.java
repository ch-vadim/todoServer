package com.ch_vadim.todoServer.store.repositories;

import com.ch_vadim.todoServer.store.entities.NotificationState;
import com.ch_vadim.todoServer.store.entities.TaskState;
import com.ch_vadim.todoServer.store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query("UPDATE UserEntity u SET u.notificationState = :state WHERE u.chatId = :chatId")
    UserEntity updateStateById(Long chatId, NotificationState state);

    @Modifying
    @Query("UPDATE UserEntity u SET u.email = :email WHERE u.chatId = :chatId")
    UserEntity updateEmailById(Long chatId, String email);

    @Query("SELECT u FROM UserEntity u JOIN FETCH u.tasks t WHERE u.notificationState != :notingState AND t.state != :doneState")
    List<UserEntity> findAllSubscribesUsersWithNotDoneTasks(@Param("notingState") NotificationState notingState, @Param("doneState") TaskState doneState);


}
