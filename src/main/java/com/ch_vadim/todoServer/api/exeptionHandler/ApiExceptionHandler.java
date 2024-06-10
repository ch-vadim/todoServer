package com.ch_vadim.todoServer.api.exeptionHandler;

import com.ch_vadim.todoServer.api.exeptions.UserAlreadyExistException;
import com.ch_vadim.todoServer.api.exeptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return ResponseEntity.
                status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(e.getMessage()));

    }
}
