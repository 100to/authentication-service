package com.example.authentication.common.exception;

public class InvalidUserException extends Exception {
    private static final String MESSAGE = "Invalid user!";

    public InvalidUserException() {
        super(MESSAGE);
    }

}
