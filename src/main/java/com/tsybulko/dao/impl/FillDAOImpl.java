package com.tsybulko.dao.impl;

import com.tsybulko.connection.ConnectionPool;
import com.tsybulko.dao.FillDAO;
import com.tsybulko.entity.CoffeeMachine;
import com.tsybulko.entity.Fill;
import com.tsybulko.entity.ingredients.Ingredient;
import com.tsybulko.entity.items.Item;
import com.tsybulko.entity.users.User;
import com.tsybulko.exceptions.DAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FillDAOImpl implements FillDAO {
    private static Properties queries = new Properties();
    private static final Logger logger = Logger.getLogger(FillDAOImpl.class);
    private static FillDAOImpl fillDAOInstance;
    private static CoffeeMachine coffeeMachine = CoffeeMachine.getCoffeeMachineInstance();
    private static int countInOnePage = 30;


    private FillDAOImpl() {
        try {
            queries.load(getClass().getResourceAsStream("/queries.properties"));
        } catch (IOException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + " Constructor", e));
            e.printStackTrace();
        }
    }

    public static FillDAO getFillDAOInstance() {
        if (fillDAOInstance == null) {
            synchronized (FillDAOImpl.class) {
            }
            if (fillDAOInstance == null)
                fillDAOInstance = new FillDAOImpl();
        }
        return fillDAOInstance;
    }

    @Override
    public void noteIngredientFill(Ingredient ingredient, int updateQuantity, User admin) {
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.add_ingredient_fills"))) {
            preparedStatement.setInt(1, updateQuantity);
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(3, ingredient.getId());
            preparedStatement.setLong(4, admin.getId());
            preparedStatement.setInt(5, coffeeMachine.getMachineId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: noteIngredientFill", e));
            e.printStackTrace();
        }
    }

    @Override
    public void noteItemFill(Item item, int updateCount, User admin) {
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.add_item_fills"))) {
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(1, updateCount);
            preparedStatement.setInt(3, item.getId());
            preparedStatement.setLong(4, admin.getId());
            preparedStatement.setInt(5, coffeeMachine.getMachineId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: noteItemFill", e));
            e.printStackTrace();
        }
    }

    @Override
    public List<Fill> getIngredientFillsLimit(int skipCount) {
        List<Fill> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.get_ingr_fills_limit"))) {
            preparedStatement.setInt(1, skipCount);
            preparedStatement.setInt(2, countInOnePage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Fill(resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getString("login"), resultSet.getDate("date")));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: getIngredientFillsLimit", e));
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Fill> getItemFillsLimit(int skipCount) {
        List<Fill> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.get_item_fills_limit"))) {
            preparedStatement.setInt(1, skipCount);
            preparedStatement.setInt(2, countInOnePage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Fill(resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getString("login"), resultSet.getDate("date")));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: getItemFillsLimit", e));
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getItemFillsLength() {
        int result = 0;
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queries.getProperty("coffee_machine.get_item_fills_length"));
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: getItemFillsLength", e));
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getIngredientFillsLength() {
        int result = 0;
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queries.getProperty("coffee_machine.get_ingr_fills_length"));
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + FillDAOImpl.class + "method: getIngredientFillsLength", e));
            e.printStackTrace();
        }
        return result;
    }
}
