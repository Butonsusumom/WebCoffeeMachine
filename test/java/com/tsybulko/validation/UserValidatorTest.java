package com.tsybulko.validation;

import com.tsybulko.builder.UserBuilder;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserValidatorTest {

    @Test(expected = ValidationException.class)
    public void isValidUserNullTest() throws ValidationException {
        User user = null;
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidNameLengthTest() throws ValidationException {
        User user = new UserBuilder().setName("a").getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidNameSymbolsTest() throws ValidationException {
        User user = new UserBuilder().setName("aa123").getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidNameNullTest() throws ValidationException {
        User user = new UserBuilder().setName(null).getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidEmailNullTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail(null).getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidEmailFormatTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail("123").getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidPasswordLengthTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail("a@a.com").setPassword("a").getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidPasswordSymbolTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail("a@a.com").setPassword("12345678/").getResult();
        new UserValidator().isValidUser(user);
    }

    @Test(expected = ValidationException.class)
    public void isValidUserInvalidPasswordNullTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail("a@a.com").setPassword(null).getResult();
        new UserValidator().isValidUser(user);
    }

    @Test
    public void isValidUserValidDataTest() throws ValidationException {
        User user = new UserBuilder().setName("oli").setEmail("a@a.com").setPassword("12345678").getResult();
        new UserValidator().isValidUser(user);
        assertTrue(true);
    }
}