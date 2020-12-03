package com.tsybulko.service;

import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;

import java.util.List;

/**
 * User service. Service for add and manipulate database users.
 */
public interface UserService {
    /**
     * Register new unique user.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void register(User user) throws ServiceException;

    /**
     * Update user.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void update(User user) throws ServiceException;

    /**
     * Delete user.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void delete(User user) throws ServiceException;

    /**
     * Get all users.
     *
     * @return list of users.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    List<User> getAll() throws ServiceException;

    /**
     * Find user by email and password.
     *
     * @param user user.
     * @return  finded user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    User signIn(User user) throws ServiceException;

    /**
     * Find user by id.
     *
     * @param user user.
     * @return  finded user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    User findById(User user) throws ServiceException;

    /**
     * Update user info by id.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void updateUserInfoById(User user) throws ServiceException;

    /**
     * Attach card account by id.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void attachCardToUserById(User user) throws ServiceException;

    /**
     * Update card account info.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void updateCardInfoById(User user) throws ServiceException;

    /**
     * Update card account amount.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void updateCardAmountById(User user) throws ServiceException;

    /**
     * Delete card.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link com.tsybulko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void deleteCardFromUserById(User user) throws ServiceException;
}
