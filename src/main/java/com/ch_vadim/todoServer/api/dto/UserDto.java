package com.ch_vadim.todoServer.api.dto;

import com.ch_vadim.todoServer.store.entities.UserEntity;
import lombok.Builder;

@Builder
public record UserDto (
        long chatId,
        String email,
        String name
){
    public static UserDto getFromUserEntity(UserEntity user) {
        return UserDto.builder()
                .chatId(user.getChatId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
