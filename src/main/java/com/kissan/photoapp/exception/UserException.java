package com.kissan.photoapp.exception;

public class UserException extends RuntimeException{
    private String errorMessage;

    public UserException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
