package com.tsybulko.dao.impl;

import com.tsybulko.connection.ConnectionPool;
import com.tsybulko.dao.ItemDAO;
import com.tsybulko.entity.items.Item;
import com.tsybulko.entity.items.impl.BigCup;
import com.tsybulko.entity.items.impl.LittleCup;
import com.tsybulko.entity.items.impl.MiddleCup;
import com.tsybulko.entity.items.impl.Stick;
import com.tsybulko.exceptions.DAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemDAOImpl implements ItemDAO {
    private static Properties queries = new Properties();
    private static final Logger logger = Logger.getLogger(ItemDAOImpl.class);
    private static ItemDAOImpl itemDAOInstance;

    private ItemDAOImpl() {
        try {
            queries.load(getClass().getResourceAsStream("/queries.properties"));
        } catch (IOException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + ItemDAOImpl.class + " constructor", e));
            e.printStackTrace();
        }
    }

    public static ItemDAOImpl getItemDAOInstance() {
        if (itemDAOInstance == null) {
            synchronized (ItemDAOImpl.class) {
                if (itemDAOInstance == null)
                    itemDAOInstance = new ItemDAOImpl();
            }
        }
        return itemDAOInstance;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<Item>() {
            {
                add(new Stick());
                add(new LittleCup());
                add(new MiddleCup());
                add(new BigCup());
            }
        };
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(queries.getProperty("coffee_machine.get_all_item"));
            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                for (Item item : list) {
                    if (itemName.equals(item.getDBName()))
                        item.setCount(resultSet.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + ItemDAOImpl.class + " method: getAllItems", e));
            e.printStackTrace();
        }
        return list;
    }

    public Item getItemByName(String name) {
        Item item = null;
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.get_item"))) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("quantity");
                switch (name) {
                    case "sticks":
                        item = new Stick(count);
                        break;
                    case "little_cups":
                        item = new LittleCup(count);
                        break;
                    case "middle_cups":
                        item = new MiddleCup(count);
                        break;
                    case "big_cups":
                        item = new BigCup(count);
                        break;
                }
                item.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + ItemDAOImpl.class + " method: getItemByName", e));
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean updateItem(String itemName, int count) {
        boolean result = true;
        if (itemName == null || itemName.equals(""))
            return false;
        try (Connection connection = ConnectionPool.getConnector().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queries.getProperty("coffee_machine.update_item"))) {
            Item item = getItemByName(itemName);
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, itemName);
            preparedStatement.executeUpdate();
            if (item.getCount() < count) {
                logger.log(Level.INFO, "Added " + (count - item.getCount()) +
                        " number of " + itemName.toUpperCase() + " into Coffee machine");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, new DAOException("DAOException class: " + ItemDAOImpl.class + " method: updateItem", e));
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
