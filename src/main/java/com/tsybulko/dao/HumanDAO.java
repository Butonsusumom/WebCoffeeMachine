package com.tsybulko.dao;

import com.tsybulko.entity.users.User;

import java.sql.Connection;

public interface HumanDAO {

    /**
     * @param user - User object to write to the database
     * @param connection - Connection object for connecting to database
     * @return - true if human added successfully
     */


    boolean insertHuman(User user, Connection connection);
}
