package com.tsybulko.connection;

import com.tsybulko.exceptions.ConnectException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Connection pool class </br>
 *
 * @author Andrii
 */

public class ConnectionPool {

    private static ConnectionPool connector;
    private InitialContext initialContext;
    private Context context;
    private DataSource dataSource;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        init();
    }

    private void init() {
        try {
            initialContext = new InitialContext();
            context = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/coffee_machine");
        } catch (NamingException e) {
            logger.log(Level.ERROR, new ConnectException("ConnectException class: " + ConnectionPool.class
                    + ", method: init(). Initialize error.", e));
            e.printStackTrace();
        }
    }

    /**
     * @return Returns ConnectionPoll class instance
     */

    public static ConnectionPool getConnector() {
        if (connector == null) {
            synchronized (ConnectionPool.class) {
                if (connector == null) {
                    connector = new ConnectionPool();
                }
            }
        }
        return connector;
    }

    /**
     * @return Returns Connection object for connecting to database
     */

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.ERROR, new ConnectException("ConnectException class: " + ConnectionPool.class
                    + ", method: getConnection().", e));
            e.printStackTrace();
        }
        return connection;
    }
}
