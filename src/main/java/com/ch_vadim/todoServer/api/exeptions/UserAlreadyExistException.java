package com.ch_vadim.todoServer.api.exeptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException (String message) {
        super(message);
    }
}
