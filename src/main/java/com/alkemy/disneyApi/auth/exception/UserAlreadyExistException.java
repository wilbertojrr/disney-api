package com.alkemy.disneyApi.auth.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
