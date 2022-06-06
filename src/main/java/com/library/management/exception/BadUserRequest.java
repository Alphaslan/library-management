package com.library.management.exception;

public class BadUserRequest extends RuntimeException {

    public BadUserRequest() {
        super("The Request for new User is invalid");
    }
}