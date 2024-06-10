package com.ch_vadim.todoServer.service;

import com.ch_vadim.todoServer.api.dto.UserDto;
import com.ch_vadim.todoServer.api.exeptions.UserAlreadyExistException;
import com.ch_vadim.todoServer.api.exeptions.UserNotFoundException;
import com.ch_vadim.todoServer.store.entities.NotificationState;
import com.ch_vadim.todoServer.store.entities.TaskEntity;
import com.ch_vadim.todoServer.store.entities.UserEntity;
import com.ch_vadim.todoServer.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity registerUser(UserDto user) {
        UserEntity userEntity = UserEntity.builder()
                .chatId(user.chatId())
                .email(user.email())
                .name(user.name())
                .build();
        Optional<UserEntity> entity = userRepository.findById(userEntity.getChatId());
        if (entity.isPresent()) throw new UserAlreadyExistException(
                "User with chatId" + userEntity.getChatId() + "already exist");
        return userRepository.saveAndFlush(userEntity);
    }

    @Transactional
    public UserEntity changeNotificationState(Long userId, NotificationState state) {
        return userRepository.updateStateById(userId, state);
    }

    @Transactional
    public UserEntity setEmail(Long userId, String email) {
        UserEntity user = userRepository.findById(userId).get();
        user.setEmail(email);
        user.setNotificationState(user.getNotificationState().changeState(NotificationState.EMAIL));
        return userRepository.saveAndFlush(user);
        
    }

    @Transactional(readOnly = true)
    public UserEntity getUserById(long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) throw new UserNotFoundException(
                "User with chatId" + id + "not fount");
        return entity.get();
    }


}

