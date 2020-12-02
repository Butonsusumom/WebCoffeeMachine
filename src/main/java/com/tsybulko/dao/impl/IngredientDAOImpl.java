package com.tsybulko.dao.impl;

import com.tsybulko.connection.ConnectionPool;
import com.tsybulko.dao.IngredientDAO;
import com.tsybulko.entity.ingredients.Ingredient;
import com.tsybulko.entity.ingredients.builder.IngredientBuilder;
import com.tsybulko.exceptions.DAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IngredientDAOImpl implements IngredientDAO {

    private static Properties queries = new Properties();
    private static final Logger logger = Logger.getLogger(IngredientDAOImpl.class);
    private static IngredientDAOImpl ingredientDAOInstance;

    private IngredientDAOImpl() {
        try {
            queries.load(getClass().getResourceAsStream("/queries.properties"));
        } catch (IOException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + IngredientDAOImpl.class + " constructor", e));
            e.printStackTrace();
        }
    }

    public static IngredientDAOImpl getIngredientDAOInstance() {
        if (ingredientDAOInstance == null) {
            synchronized (IngredientDAOImpl.class) {
                if (ingredientDAOInstance == null)
                    ingredientDAOInstance = new IngredientDAOImpl();
            }
        }
        return ingredientDAOInstance;
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queries.getProperty("coffee_machine.get_all_ingredients"));
            while (resultSet.next()) {
                ingredients.add(new IngredientBuilder()
                        .setName(resultSet.getString("name"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setId(resultSet.getInt("id"))
                        .setExpirationDate(resultSet.getDate("expiration_date"))
                        .build()
                );
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + IngredientDAOImpl.class + " method: getAllIngredients", e));
            e.printStackTrace();
        }
        return ingredients;
    }

    public Ingredient getIngredientByName(String ingredientName) {
        Ingredient ingredient = null;
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.get_ingredient"))) {
            preparedStatement.setString(1, ingredientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ingredient = new IngredientBuilder()
                        .setName(resultSet.getString("name"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setId(resultSet.getInt("id"))
                        .setExpirationDate(resultSet.getDate("expiration_date"))
                        .build();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + IngredientDAOImpl.class + " method: getIngredientByName", e));
            e.printStackTrace();
        }
        return ingredient;
    }

    @Override
    public boolean updateIngredient(String ingredientName, int quantity) {
        boolean result = true;
        if (ingredientName == null || ingredientName.equals("")) {
            return false;
        }
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.update_ingredient"))) {
            Ingredient ingredient = getIngredientByName(ingredientName);
            preparedStatement.setLong(1, quantity);
            if (ingredient.getQuantity() < quantity) {
                ingredient.setExpirationDate(new Date(System.currentTimeMillis() + ingredient.getMaxExpirationDate()));
            }
            preparedStatement.setDate(2, (Date) ingredient.getExpirationDate());
            preparedStatement.setString(3, ingredientName);
            preparedStatement.executeUpdate();
            if (ingredient.getQuantity() < quantity) {
                logger.log(Level.INFO, "Added " + (quantity - ingredient.getQuantity()) +
                        " number of " + ingredientName.toUpperCase() + " into Coffee machine");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + IngredientDAOImpl.class + " method: updateIngredient", e));
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
