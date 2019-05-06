package com.kensplanet.familytreeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberException(MemberNotFoundException memberNotFoundException) {
        return new ResponseEntity<>(memberNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("Technical issues", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
