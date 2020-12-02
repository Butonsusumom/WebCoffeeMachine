package com.tsybulko.exceptions;

public class DAOException extends RuntimeException {

    private String message;

    public DAOException(String message, Exception e) {
        super(e);
        this.message = message;
    }

    @Override
    public String toString() {
        return message + "\n" + super.getLocalizedMessage();
    }
}
