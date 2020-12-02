package com.tsybulko.dao;

import com.tsybulko.entity.users.User;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 * DAO for operations with User objects
 */

public interface UserDAO {

    /**
     * @param user - User object to write to the database
     * @param connection - Connection object for connecting to database
     * @return - true if user added successfully
     */

    boolean addUser(User user, Connection connection);

    /**
     * @param user - User object to write to the database
     * @param connection - Connection object for connecting to database
     * @return - true if the dependency machine has person added successfully
     */

    boolean addMachineHasUser(User user, Connection connection);

    /**
     * @param login - The login of the User
     * @return - Object of User from database
     */

    User getUserByLogin(String login);

    /**
     * @param balance - The amount which the account is replenished
     * @param login - The login of the user
     * @return - Returns true if balance updated successfully
     */

    boolean updateBalance(BigDecimal balance, String login);
}
