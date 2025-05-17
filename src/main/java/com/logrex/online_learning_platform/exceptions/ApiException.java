package com.logrex.online_learning_platform.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

