package com.tsybulko.dao;

import com.tsybulko.exception.DAOException;

import java.util.List;

/**
 * DAO interface.
 *
 * @param <T> some entity.
 */
public interface CommonDAO<T> {
    /**
     * Create object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    void create(T element) throws DAOException;

    /**
     * Update object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    void update(T element) throws DAOException;

    /**
     * Delete object.
     *
     * @param element object.
     * @throws DAOException dao exception.
     */
    void delete(T element) throws DAOException;

    /**
     * Find all objects.
     *
     * @return list of objects.
     * @throws DAOException dao exception.
     */
    List<T> getAll() throws DAOException;
}
