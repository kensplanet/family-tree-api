package com.kensplanet.familytreeapi.exception;

public class MemberException extends RuntimeException {

    private String message;

    public MemberException(String message) {
        super(message);
        this.message= message;
    }

    public String getMessage() {
        return message;
    }
}
