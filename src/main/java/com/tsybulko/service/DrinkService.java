package com.tsybulko.service;

import com.tsybulko.entity.Drink;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;

import java.util.List;

/**
 * Drink service. Service for add and manipulate database drinks.
 */
public interface DrinkService {

    /**
     * Add drink.
     *
     * @param drink drink.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void add(Drink drink) throws ServiceException;

    /**
     * Update drink.
     *
     * @param drink drink.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void update(Drink drink) throws ServiceException;

    /**
     * Delete drink.
     *
     * @param drink drink.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void delete(Drink drink) throws ServiceException;

    /**
     * Get all drinks.
     *
     * @return list of drinks.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    List<Drink> getAll() throws ServiceException;

    /**
     * Find drink by id.
     *
     * @param drink drink.
     * @return finded drink.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    Drink findById(Drink drink) throws ServiceException;

    /**
     * Get all drinks ordered by user.
     *
     * @param user user.
     * @return list of drinks.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    List<Drink> getPurchaseHistoryByUserId(User user) throws ServiceException;
}
