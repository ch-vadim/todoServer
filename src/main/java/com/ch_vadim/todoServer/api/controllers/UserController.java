package com.ch_vadim.todoServer.api.controllers;

import com.ch_vadim.todoServer.api.dto.UserDto;
import com.ch_vadim.todoServer.api.exeptions.UserAlreadyExistException;
import com.ch_vadim.todoServer.service.UserService;
import com.ch_vadim.todoServer.store.entities.NotificationState;
import com.ch_vadim.todoServer.store.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserDto user) {
        try {
            userService.registerUser(user);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{chat_id}/state")
    public ResponseEntity<Void> changeNotificationStatus(
            @PathVariable Long chat_id,
            @RequestParam NotificationState state
    ) {

        userService.changeNotificationState(chat_id, state);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("/{chat_id}/email")
    public ResponseEntity<Void> changeEmail(
            @PathVariable Long chat_id,
            @RequestParam String email
    ) {

        userService.setEmail(chat_id, email);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        UserEntity entity = userService.getUserById(id);
        return  UserDto.getFromUserEntity(entity);
    }
}
