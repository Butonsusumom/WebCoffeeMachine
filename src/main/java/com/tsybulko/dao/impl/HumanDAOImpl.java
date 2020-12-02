package com.tsybulko.dao.impl;

import com.tsybulko.dao.HumanDAO;
import com.tsybulko.entity.users.User;
import com.tsybulko.exceptions.DAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class HumanDAOImpl implements HumanDAO {

    private static Properties queries = new Properties();
    private static final Logger logger = Logger.getLogger(FillDAOImpl.class);
    private static HumanDAO humanDAOInstance;

    private HumanDAOImpl() {
        try {
            queries.load(getClass().getResourceAsStream("/queries.properties"));
        } catch (IOException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + HumanDAOImpl.class + " constructor", e));
            e.printStackTrace();
        }
    }

    public static HumanDAO getHumanDAOInstance() {
        if (humanDAOInstance == null) {
            synchronized (HumanDAOImpl.class) {
                if (humanDAOInstance == null)
                    humanDAOInstance = new HumanDAOImpl();
            }
        }
        return humanDAOInstance;
    }

    @Override
    public boolean insertHuman(User user, Connection connection) {
        boolean result = true;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.add_human"))) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + HumanDAOImpl.class + "method: insertHuman", e));
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
