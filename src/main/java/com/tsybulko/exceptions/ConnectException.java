package com.tsybulko.exceptions;

public class ConnectException extends RuntimeException {

    private String message;

    public ConnectException(String message, Exception e) {
        super(e);
        this.message = message;
    }

    @Override
    public String toString() {
        return message + "\n" + super.getLocalizedMessage();
    }
}
