package com.tsybulko.dao.impl;

import com.tsybulko.builder.CardAccountBuilder;
import com.tsybulko.builder.UserBuilder;
import com.tsybulko.dao.AbstractCommonDAO;
import com.tsybulko.dao.UserDAO;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAOImpl extends AbstractCommonDAO<User> implements UserDAO {

    private Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String INSERT_USER =
            "INSERT INTO `user` (`name`, `email`, `password`, `is_active`, `role_id`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_BY_ID =
            "UPDATE `user` SET `is_active`=? WHERE id=?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id` = ?";

    private static final String FIND_ALL_USERS =
            "SELECT `user`.`id`, `name`, `email`, `password`, `is_active`," +
                    " `card_account`.`id` as `card_id`, `number` as `card_number`, `amount` as `card_amount`," +
                    " `title` as `role_title` " +
                    "FROM `user` join `role` on `user`.`role_id` = `role`.`id`" +
                    "left join `card_account` on `user`.`card_account_id` = `card_account`.`id`";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            FIND_ALL_USERS + "WHERE `email`=? AND `password`=?";

    private static final String FIND_USER_BY_EMAIL =
            FIND_ALL_USERS + "WHERE `email`=?";

    private static final String FIND_USER_BY_ID =
            FIND_ALL_USERS + "WHERE `user`.`id`=?";

    private static final String UPDATE_USER_INFO_BY_ID =
            "UPDATE `user` SET `name`=?, `email`=?, `password`=? WHERE id=?";

    private static final String INSERT_CARD =
            "INSERT INTO `card_account` (`number`, `amount`) VALUES (?, ?)";

    private static final String UPDATE_USER_CARD_BY_ID =
            "UPDATE `user` SET `card_account_id`=? WHERE id=?";

    private static final String UPDATE_CARD_NUMBER_BY_ID =
            "UPDATE `card_account` SET `number`=? WHERE id=?";

    private static final String UPDATE_CARD_AMOUNT_BY_ID =
            "UPDATE `card_account` SET `amount`=? WHERE id=?";

    private static final String DELETE_CARD_BY_ID =
            "DELETE FROM `card_account` WHERE id=?";

    private static final String DELETE_CARD_FROM_USER_BY_ID =
            "UPDATE `user` SET `card_account_id`=null WHERE id=?";

    @Override
    public User findByEmailAndPassword(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByEmailAndPasswordStatement(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public User findByEmail(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByEmailStatement(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public User findById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByIdStatement(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public void updateUserInfoById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateUserInfoByIdStatement(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public void attachCardToUserById(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            executeUpdateUserCardById(connection, user, executeAddCard(connection, user));
            connection.commit();
        } catch (SQLException ex) {
            rollbackTransaction(connection);
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }

    private int executeAddCard(Connection connection, User user) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = buildAddCardStatement(connection, user);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
        }
    }

    private void executeUpdateUserCardById(Connection connection, User user, int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildUpdateUserCardByIdStatement(connection, user, id);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void updateCardInfoById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateCardNumberByIdStatement(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public void updateCardAmountById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateCardAmountByIdStatement(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public void deleteCardFromUserById(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            executeDeleteCardFromUserById(connection, user);
            executeDeleteCardById(connection, user);
            connection.commit();
        } catch (SQLException ex) {
            rollbackTransaction(connection);
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection);
            closeStatement(statement);
            closeResultSet(resultSet);
        }
    }

    private void executeDeleteCardFromUserById(Connection connection, User user) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildDeleteCardFromUserByIdStatement(connection, user);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    private void executeDeleteCardById(Connection connection, User user) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildDeleteCardByIdStatement(connection, user);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    protected PreparedStatement buildInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getName());
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        preparedStatement.setBoolean(++statementIndex, user.getActivity());
        preparedStatement.setInt(++statementIndex, user.getRole().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildUpdateByIDStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setBoolean(++statementIndex, user.getActivity());
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildDeleteByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        preparedStatement.setInt(1, user.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_USERS);
    }

    protected PreparedStatement buildFindByEmailAndPasswordStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        return preparedStatement;
    }

    protected PreparedStatement buildFindByEmailStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getEmail());
        return preparedStatement;
    }

    protected PreparedStatement buildFindByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateUserInfoByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INFO_BY_ID);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getName());
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildAddCardStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD, Statement.RETURN_GENERATED_KEYS);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getCardAccount().getCardNumber());
        preparedStatement.setBigDecimal(++statementIndex, user.getCardAccount().getAmount());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateUserCardByIdStatement(Connection connection, User user, int cardId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_CARD_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, cardId);
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateCardNumberByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARD_NUMBER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getCardAccount().getCardNumber());
        preparedStatement.setInt(++statementIndex, user.getCardAccount().getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateCardAmountByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARD_AMOUNT_BY_ID);
        int statementIndex = 0;
        preparedStatement.setBigDecimal(++statementIndex, user.getCardAccount().getAmount());
        preparedStatement.setInt(++statementIndex, user.getCardAccount().getId());
        return preparedStatement;
    }

    protected PreparedStatement buildDeleteCardByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD_BY_ID);
        preparedStatement.setInt(1, user.getCardAccount().getId());
        return preparedStatement;
    }

    protected PreparedStatement buildDeleteCardFromUserByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD_FROM_USER_BY_ID);
        preparedStatement.setInt(1, user.getId());
        return preparedStatement;
    }

    @Override
    protected User createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setActivity(resultSet.getBoolean("is_active"))
                .setCardAccount(new CardAccountBuilder()
                        .setId(resultSet.getInt("card_id"))
                        .setCardNumber(resultSet.getString("card_number"))
                        .setAmount(resultSet.getBigDecimal("card_amount"))
                        .getResult())
                .setRole(resultSet.getString("role_title"))
                .getResult();
    }
}
