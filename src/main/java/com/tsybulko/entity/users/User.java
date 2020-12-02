package com.tsybulko.entity.users;

import java.math.BigDecimal;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private BigDecimal balance;
    private Role role;
    private Long id;

    public User(String login, String password, String firstName, String lastName, String middleName,BigDecimal balance,Role role, Long id) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.balance = balance;
        this.role = role;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getId() { return id; }

    public Role getRole(){ return role; }

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    public void setId(Long id){ this.id = id; }

    public void setPassword(String password){ this.password = password; }

    public boolean isCustomer() {
        return role == Role.CUSTOMER;
    }

    public boolean isAdmin(){
        return role == Role.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (middleName != null ? !middleName.equals(user.middleName) : user.middleName != null) return false;
        if (role != user.role) return false;
        return id != null ? id.equals(user.id) : user.id == null;
    }
}
