package com.gymcrm.system.service.exception;

public class RequestInvalidParamException extends RuntimeException {
    public RequestInvalidParamException(String message) {
        super(message);
    }
}
