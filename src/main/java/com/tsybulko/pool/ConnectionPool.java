package com.tsybulko.pool;

import com.tsybulko.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Connection pool, which contains connections of ProxyConnection type.
 * Implemented as singleton.
 *
 * @see ProxyConnection
 */
public enum ConnectionPool {
    /**
     * Singleton. Instance.
     */
    INSTANCE;

    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(ConnectionPool.class);
    /**
     * Contains connections, which are available for use.
     */
    private BlockingQueue<ProxyConnection> availableConnections;
    /**
     * Contains connections, which are used and aren't available to take.
     */
    private Queue<ProxyConnection> usedConnections;
    /**
     * Pool size.
     */
    private static final int POOL_SIZE = 16;
    /**
     * Variable to synchronize pool initializing.
     */
    private AtomicBoolean isInitialized = new AtomicBoolean(false);
    /**
     * Source for database properties.
     */
    private static final String DATABASE_PROPERTIES_FILE = "database.properties";
    private static final String DATABASE_URL = "url";
    private static final String DATABASE_DRIVER = "driver";

    /**
     * Constructor with initializing.
     */
    ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    /**
     * Initialize a connection pool.
     * Can throw ConnectionPoolException.
     *
     * @see ConnectionPoolException
     */
    public void init() throws ConnectionPoolException {
        if (!isInitialized.get()) {
            Properties properties = new Properties();
            ClassLoader classLoader = this.getClass().getClassLoader();
            try {
                properties.load(classLoader.getResourceAsStream(DATABASE_PROPERTIES_FILE));
                String url = properties.getProperty(DATABASE_URL);
                String driver = properties.getProperty(DATABASE_DRIVER);
                Class.forName(driver);
                for (int i = 0; i < POOL_SIZE; i++) {
                    availableConnections.add(new ProxyConnection(DriverManager.getConnection(url,properties)));
                }
                isInitialized.set(true);
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                logger.fatal(ex);
                throw new ConnectionPoolException("Failed to initialize pool", ex);
            }
        }
    }

    /**
     * Get-method to get connection. Takes available connection
     * from container, if it has available connection, put this connection
     * to using connection container. If available
     * connections container hasn't any connection, tries to create new
     * connection. Then returns this connection.
     *
     * @return a connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.poll(60, TimeUnit.SECONDS);
            usedConnections.add(connection);
        } catch (InterruptedException ex) {
            logger.error(ex);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Receive a connection, check it and put into available
     * connections container.
     *
     * @param connection received connection.
     */
    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection.getClass() != ProxyConnection.class) {
            throw new ConnectionPoolException("Invalid entered connection");
        }
        usedConnections.remove(connection);
        availableConnections.offer((ProxyConnection) connection);
    }

    /**
     * Closes connections.
     */
    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                availableConnections.take().realClose();
            } catch (SQLException | InterruptedException ex) {
                logger.error(ex);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException ex) {
                logger.warn(ex);
            }
        }
    }

}
