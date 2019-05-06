package com.kensplanet.familytreeapi.exception;

public class MemberNotFoundException extends RuntimeException {

    private String message;

    public MemberNotFoundException(String message) {
        super(message);
        this.message= message;
    }

    public String getMessage() {
        return message;
    }
}
