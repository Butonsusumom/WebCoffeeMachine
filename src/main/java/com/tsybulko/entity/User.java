package com.tsybulko.entity;

/**
 * User.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean activity;
    private CardAccount cardAccount;
    private Role role;

    public User() {
    }

    public User(int id, String name, String email, String password, boolean activity, CardAccount cardAccount, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.activity = activity;
        this.cardAccount = cardAccount;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public CardAccount getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                activity == user.activity &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                cardAccount.equals(user.cardAccount) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (activity ? 1 : 0);
        result = 31 * result + (cardAccount != null ? cardAccount.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", activity=" + activity +
                ", cardAccount=" + cardAccount +
                ", role=" + role +
                '}';
    }


}
