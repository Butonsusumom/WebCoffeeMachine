package com.tsybulko.validation;

import com.tsybulko.entity.User;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ValidationException;

public class UserValidator {

    private static final String USER_NAME_REGEX = "^[a-zA-Zа-яА-Я-]{2,20}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_]+@(.+)$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{8,20}$";
    private static final int MIN_ID = 1;

    public void isValidUser(User user) throws ValidationException {
        isNotNull(user);
        isValidName(user);
        isValidEmail(user);
        isValidPassword(user);
    }

    public void isValidUserWithoutPassword(User user) throws ValidationException {
        isNotNull(user);
        isValidName(user);
        isValidEmail(user);
    }

    public void isValidEmailAndPassword(User user) throws ValidationException {
        isNotNull(user);
        isValidEmail(user);
        isValidPassword(user);
    }

    public void isValidUserId(User user) throws ValidationException {
        isNotNull(user);
        isValidId(user);
    }

    private void isNotNull(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException(ExceptionsValue.NULL_USER.toString());
        }
    }

    private void isValidName(User user) throws ValidationException {
        if (user.getName() == null || !user.getName().matches(USER_NAME_REGEX)) {
            throw new ValidationException(ExceptionsValue.INVALID_USER_NAME.toString());
        }
    }

    private void isValidEmail(User user) throws ValidationException {
        if (user.getEmail() == null || !user.getEmail().matches(EMAIL_REGEX)) {
            throw new ValidationException(ExceptionsValue.INVALID_USER_EMAIL.toString());
        }
    }

    private void isValidPassword(User user) throws ValidationException {
        if (user.getPassword() == null || !user.getPassword().matches(PASSWORD_REGEX)) {
            throw new ValidationException(ExceptionsValue.INVALID_USER_PASSWORD.toString());
        }
    }

    private void isValidId(User user) throws ValidationException {
        if (user.getId() < MIN_ID) {
            throw new ValidationException(ExceptionsValue.INVALID_USER_ID.toString());
        }
    }
}
