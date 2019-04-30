package com.kensplanet.familytreeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@ControllerAdvice
public class GlobalExceptionHandler {



    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Parent already exists.") //500
    @ExceptionHandler(CoupleAlreadyExistsException.class)
    public void handleException() {
    }*/


/*
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoupleAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Parent already exists.") //500
    public void handleNotFoundException(CoupleAlreadyExistsException ex) {


    }
}*/
