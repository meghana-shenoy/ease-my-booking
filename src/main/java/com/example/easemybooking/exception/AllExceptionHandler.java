package com.example.easemybooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
