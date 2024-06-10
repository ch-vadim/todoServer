package com.ch_vadim.todoServer.api.exeptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String message) {
        super(message);
    }
}
