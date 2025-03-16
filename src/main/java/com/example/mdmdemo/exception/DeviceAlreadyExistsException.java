package com.example.mdmdemo.exception;

public class DeviceAlreadyExistsException extends RuntimeException{
    public DeviceAlreadyExistsException() {
        super();
    }

    public DeviceAlreadyExistsException(String message) {
        super(message);
    }

    public DeviceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
