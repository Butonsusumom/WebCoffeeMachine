package com.tsybulko.exceptions;

public class ServiceException extends RuntimeException {

    private String message;

    public ServiceException(String message, Exception e) {
        super(e);
        this.message = message;
    }

    @Override
    public String toString() {
        return message + "\n" + super.getLocalizedMessage();
    }
}
