package com.tsybulko.entity.users.buider;

import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;

import java.math.BigDecimal;

public class UserBuilder {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private BigDecimal balance;
    private Role role;
    private Long id;

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public UserBuilder setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public User build() {
        return new User(login, password, firstName, lastName, middleName, balance, role, id);
    }
}
