package com.kensplanet.familytreeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFoundException(MemberNotFoundException memberNotFoundException) {
        return new ResponseEntity<>(memberNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberEditException.class)
    public ResponseEntity<String> handleMemberEditException(MemberEditException memberEditException) {
        return new ResponseEntity<>(memberEditException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("Technical issues", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
