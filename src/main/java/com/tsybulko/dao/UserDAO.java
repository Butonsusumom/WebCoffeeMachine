package com.tsybulko.dao;

import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;

/**
 * User dao interface.
 */
public interface UserDAO {
    /**
     * Find user by email and password.
     *
     * @param user entity with email and password
     * @return finding user
     * @throws DAOException dao exception.
     */
    User findByEmailAndPassword(User user) throws DAOException;

    /**
     * Find user by email.
     *
     * @param user entity with email
     * @return finding user
     * @throws DAOException dao exception.
     */
    User findByEmail(User user) throws DAOException;

    /**
     * Find user by id.
     *
     * @param user entity with id
     * @return finding user
     * @throws DAOException dao exception.
     */
    User findById(User user) throws DAOException;

    /**
     * Update user info by id.
     *
     * @param user entity with id
     * @throws DAOException dao exception.
     */
    void updateUserInfoById(User user) throws DAOException;

    /**
     * Add new card to user by id.
     *
     * @param user entity with id
     * @throws DAOException dao exception.
     */
    void attachCardToUserById(User user) throws DAOException;

    /**
     * Update user card info by id.
     *
     * @param user entity with id
     * @throws DAOException dao exception.
     */
    void updateCardInfoById(User user) throws DAOException;

    /**
     * Update user card amount info by id.
     *
     * @param user entity with id
     * @throws DAOException dao exception.
     */
    void updateCardAmountById(User user) throws DAOException;

    /**
     * Delete user card by id.
     *
     * @param user entity with id and card
     * @throws DAOException dao exception.
     */
    void deleteCardFromUserById(User user) throws DAOException;
}
