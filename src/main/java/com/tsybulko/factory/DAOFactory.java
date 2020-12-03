package com.tsybulko.factory;

import com.tsybulko.dao.impl.DrinkDAOImpl;
import com.tsybulko.dao.impl.OrderDAOImpl;
import com.tsybulko.dao.impl.UserDAOImpl;

/**
 * Factory for DAO.
 */
public enum DAOFactory {
    INSTANCE;

    private UserDAOImpl userDAO = new UserDAOImpl();
    private DrinkDAOImpl drinkDAO = new DrinkDAOImpl();
    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public DrinkDAOImpl getDrinkDAO() {
        return drinkDAO;
    }

    public OrderDAOImpl getOrderDAO() {
        return orderDAO;
    }
}
