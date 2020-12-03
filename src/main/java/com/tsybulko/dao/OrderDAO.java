package com.tsybulko.dao;

import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;

import java.util.List;

/**
 * Drink dao interface.
 */
public interface OrderDAO {
    /**
     * Find order that user confirm by user id.
     *
     * @param user entity with id
     * @return finded order list
     * @throws DAOException dao exception.
     */
    List<Order> findAllOrdersByUserId(User user) throws DAOException;

    /**
     * Confirm user shopping cart.
     *
     * @param cart list of user orders
     * @throws DAOException dao exception.
     */
    void checkoutCart(List<Order> cart) throws DAOException;
}
