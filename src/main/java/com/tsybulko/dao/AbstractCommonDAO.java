package com.tsybulko.dao;

import com.tsybulko.exception.DAOException;
import com.tsybulko.pool.ConnectionPool;
import com.tsybulko.pool.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class dao. Base class for all DAO.
 *
 * @param <T> some entity.
 */
public abstract class AbstractCommonDAO<T> implements CommonDAO<T> {
    /**
     * Connection Pool object.
     */
    protected ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(AbstractCommonDAO.class);

    /**
     * Create object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    @Override
    public void create(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildInsertStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while adding element", ex);
        }
    }

    /**
     * Update object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    @Override
    public void update(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateByIDStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while updating element", ex);
        }
    }

    /**
     * Delete object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    @Override
    public void delete(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildDeleteByIdStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while deleting element", ex);
        }
    }

    /**
     * Find all object.
     *
     * @return list of objects.
     * @throws DAOException dao exception.
     */
    @Override
    public List<T> getAll() throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindAllStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createEntityFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting all elements", ex);
        }
    }

    /**
     * Close result set.
     *
     * @param resultSet object.
     */
    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                logger.error("Cannot close resultSet", ex);
            }
        }
    }

    /**
     * Close statement.
     *
     * @param statement object.
     */
    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                logger.error("Cannot close PreparedStatement", ex);
            }
        }
    }

    /**
     * Close connection.
     *
     * @param connection object.
     */
    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                ((ProxyConnection) connection).close();
            } catch (ClassCastException ex) {
                logger.error("Invalid connection", ex);
            }
        }
    }

    /**
     * Rollback transaction.
     *
     * @param connection object.
     */
    protected void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Build prepared statement for insert method.
     *
     * @param connection
     * @param element
     * @return builded statement
     */
    protected abstract PreparedStatement buildInsertStatement(Connection connection, T element) throws SQLException;

    /**
     * Build prepared statement for updating method.
     *
     * @param connection
     * @param element
     * @return builded statement
     */
    protected abstract PreparedStatement buildUpdateByIDStatement(Connection connection, T element) throws SQLException;

    /**
     * Build prepared statement for deleting method.
     *
     * @param connection
     * @param element
     * @return builded statement
     */
    protected abstract PreparedStatement buildDeleteByIdStatement(Connection connection, T element) throws SQLException;

    /**
     * Build prepared statement for finding all method.
     *
     * @param connection
     * @return builded statement
     */
    protected abstract PreparedStatement buildFindAllStatement(Connection connection) throws SQLException;

    /**
     * Build entity from result set.
     *
     * @param resultSet
     * @return builded entity
     */
    protected abstract T createEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
