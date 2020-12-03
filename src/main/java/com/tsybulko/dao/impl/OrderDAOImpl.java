package com.tsybulko.dao.impl;

import com.tsybulko.builder.DrinkBuilder;
import com.tsybulko.builder.OrderBuilder;
import com.tsybulko.builder.UserBuilder;
import com.tsybulko.dao.AbstractCommonDAO;
import com.tsybulko.dao.OrderDAO;
import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractCommonDAO<Order> implements OrderDAO {

    private Logger logger = Logger.getLogger(OrderDAOImpl.class);

    private static final String INSERT_ORDER =
            "INSERT INTO `drink_shopping_cart` (`user_id`, `drinks_id`) VALUES (?, ?)";

    private static final String UPDATE_ORDER_BY_ID = "UPDATE `drink_shopping_cart` SET `drinks_id`=? WHERE `id` = ?";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM `drink_shopping_cart` WHERE `id` = ?";

    private static final String FIND_ALL_ORDERS =
            "SELECT `drink_shopping_cart`.`id`, `user_id`, `drinks_id`, `title`, `price`, `volume`" +
                    "FROM `drink_shopping_cart` JOIN `drinks` ON `drink_shopping_cart`.`drinks_id`=`drinks`.`id`" +
                    "JOIN `drink_size` ON `drinks`.`drink_size_id` = `drink_size`.`id`";

    private static final String FIND_ALL_ORDERS_BY_USER_ID =
            FIND_ALL_ORDERS + "WHERE `user_id`=?";

    private static final String INSERT_ORDER_IN_SALES =
            "INSERT INTO `drink_sales` (`user_id`, `drinks_id`) VALUES (?, ?)";

    private static final String UPDATE_SERVING_NUMBER_BY_ID =
            "UPDATE `drinks` SET `serving_number` = `serving_number`-1 WHERE `id`=?";

    private static final String UPDATE_USER_CARD_AMOUNT_BY_ID =
            "UPDATE `card_account` SET `amount` = `amount`-? " +
                    "WHERE `id`=(SELECT `card_account_id` FROM `user` WHERE `id`=?)";

    @Override
    public List<Order> findAllOrdersByUserId(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindAllOrdersByUserIdStatement(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            List<Order> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createEntityFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public void checkoutCart(List<Order> cart) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            for (Order order : cart) {
                executeInsertOrderInSales(connection, order);
                executeUpdateServingNumberById(connection, order);
                executeDeleteById(connection, order);
                executeUpdateUserCardAmountById(connection, order);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollbackTransaction(connection);
            logger.error(ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection);
        }
    }

    private void executeInsertOrderInSales(Connection connection, Order order) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildInsertOrderInSalesStatement(connection, order);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    private void executeUpdateServingNumberById(Connection connection, Order order) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildUpdateServingNumberByIdStatement(connection, order);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    private void executeDeleteById(Connection connection, Order order) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildDeleteByIdStatement(connection, order);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    private void executeUpdateUserCardAmountById(Connection connection, Order order) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = buildUpdateUserCardAmountByIdStatement(connection, order);
            statement.executeUpdate();
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    protected PreparedStatement buildInsertStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, order.getUser().getId());
        preparedStatement.setInt(++statementIndex, order.getDrink().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildUpdateByIDStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, order.getDrink().getId());
        preparedStatement.setInt(++statementIndex, order.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildDeleteByIdStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
        preparedStatement.setInt(1, order.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_ORDERS);
    }

    protected PreparedStatement buildFindAllOrdersByUserIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_BY_USER_ID);
        preparedStatement.setInt(1, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildInsertOrderInSalesStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_IN_SALES);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, order.getUser().getId());
        preparedStatement.setInt(++statementIndex, order.getDrink().getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateServingNumberByIdStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVING_NUMBER_BY_ID);
        preparedStatement.setInt(1, order.getDrink().getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateUserCardAmountByIdStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_CARD_AMOUNT_BY_ID);
        int statementIndex = 0;
        preparedStatement.setBigDecimal(++statementIndex, order.getDrink().getPrice());
        preparedStatement.setInt(++statementIndex, order.getUser().getId());
        return preparedStatement;
    }

    @Override
    protected Order createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .setId(resultSet.getInt("id"))
                .setUser(new UserBuilder()
                        .setId(resultSet.getInt("user_id"))
                        .getResult())
                .setDrink(new DrinkBuilder()
                        .setId(resultSet.getInt("drinks_id"))
                        .setTitle(resultSet.getString("title"))
                        .setPrice(resultSet.getBigDecimal("price"))
                        .setDrinkSize(resultSet.getString("volume"))
                        .getResult())
                .getResult();
    }
}
