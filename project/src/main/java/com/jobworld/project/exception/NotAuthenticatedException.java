package com.jobworld.project.exception;

import org.springframework.security.core.AuthenticationException;

public class NotAuthenticatedException extends AuthenticationException {

    public NotAuthenticatedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NotAuthenticatedException(String msg) {
        super(msg);
    }

}
