package com.kensplanet.familytreeapi.exception;

public class MemberEditException extends RuntimeException {

    private String message;

    public MemberEditException(String message) {
        super(message);
        this.message= message;
    }

    public String getMessage() {
        return message;
    }
}
