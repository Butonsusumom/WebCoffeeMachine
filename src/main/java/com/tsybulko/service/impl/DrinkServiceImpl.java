package com.tsybulko.service.impl;

import com.tsybulko.dao.AbstractCommonDAO;
import com.tsybulko.dao.DrinkDAO;
import com.tsybulko.entity.Drink;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.exception.ValidationException;
import com.tsybulko.factory.DAOFactory;
import com.tsybulko.service.DrinkService;
import com.tsybulko.validation.DrinkValidator;
import com.tsybulko.validation.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {

    private Logger logger = Logger.getLogger(DrinkServiceImpl.class);
    private AbstractCommonDAO<Drink> drinkDAO = DAOFactory.INSTANCE.getDrinkDAO();
    private DrinkValidator drinkValidator = new DrinkValidator();
    private UserValidator userValidator = new UserValidator();

    @Override
    public void add(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkData(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findByTitleAndSizeAndPrice(drink);
            if (existDrink == null) {
                drinkDAO.create(drink);
            } else {
                drink.setId(existDrink.getId());
                update(drink);
            }
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidIdAndServingNumber(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findById(drink);
            if (existDrink == null) {
                logger.error(ExceptionsValue.NOT_EXIST_DRINK.toString());
                throw new ServiceException(ExceptionsValue.NOT_EXIST_DRINK.toString());
            } else {
                drink.setServingNumber(drink.getServingNumber() + existDrink.getServingNumber());
                drinkDAO.update(drink);
            }
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            drinkDAO.delete(drink);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Drink> getAll() throws ServiceException {
        try {
            return drinkDAO.getAll();
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        }
    }

    @Override
    public Drink findById(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            return ((DrinkDAO) drinkDAO).findById(drink);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Drink> getPurchaseHistoryByUserId(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            return ((DrinkDAO) drinkDAO).findHistoryByUserId(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}

