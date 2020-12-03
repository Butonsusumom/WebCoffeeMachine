package com.tsybulko.builder;

import com.tsybulko.entity.CardAccount;
import com.tsybulko.entity.Role;
import com.tsybulko.entity.User;

/**
 * User builder.
 */

public class UserBuilder {
    /**
     * Id.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * Email.
     */
    private String email;
    /**
     * Password.
     */
    private String password;
    /**
     * Activity.
     */
    private boolean activity;
    /**
     * Card Account.
     */
    private CardAccount cardAccount;
    /**
     * Role.
     */
    private Role role;

    /**
     * Constructor without parameters.
     */
    public UserBuilder() {
    }

    /**
     * Setter.
     *
     * @param id entity id.
     * @return @see UserBuilder
     */
    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Setter.
     *
     * @param name entity name.
     * @return @see UserBuilder
     */
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Setter.
     *
     * @param email entity email.
     * @return @see UserBuilder
     */
    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Setter.
     *
     * @param password entity password.
     * @return @see UserBuilder
     */
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Setter.
     *
     * @param activity entity activity.
     * @return @see UserBuilder
     */
    public UserBuilder setActivity(boolean activity) {
        this.activity = activity;
        return this;
    }

    /**
     * Setter.
     *
     * @param cardAccount entity card account.
     * @return @see UserBuilder
     */
    public UserBuilder setCardAccount(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
        return this;
    }

    /**
     * Setting role by @see #Role enum constant.
     *
     * @param role entity role.
     * @return @see UserBuilder
     */
    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    /**
     * Setting role by role id in DB.
     *
     * @param id entity role.
     * @return @see UserBuilder
     */
    public UserBuilder setRole(int id) {
        this.role = Role.getRoleById(id);
        return this;
    }

    /**
     * Setting role by role title.
     *
     * @param role entity role.
     * @return @see UserBuilder
     */
    public UserBuilder setRole(String role) {
        this.role = Role.valueOf(role);
        return this;
    }

    /**
     * Getter.
     *
     * @return @see UserBuilder with builded poles.
     */
    public User getResult() {
        return new User(id, name, email, password, activity, cardAccount, role);
    }
}
