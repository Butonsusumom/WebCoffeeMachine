package com.tsybulko.service.impl;

import com.tsybulko.dao.AbstractCommonDAO;
import com.tsybulko.dao.OrderDAO;
import com.tsybulko.dao.UserDAO;
import com.tsybulko.entity.Drink;
import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.exception.ValidationException;
import com.tsybulko.factory.DAOFactory;
import com.tsybulko.service.OrderService;
import com.tsybulko.validation.OrderValidation;
import com.tsybulko.validation.PurchaseValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private AbstractCommonDAO<Order> orderDAO = DAOFactory.INSTANCE.getOrderDAO();
    private AbstractCommonDAO<User> userDAO = DAOFactory.INSTANCE.getUserDAO();
    private AbstractCommonDAO<Drink> drinkDAO = DAOFactory.INSTANCE.getDrinkDAO();
    private OrderValidation orderValidation = new OrderValidation();
    private PurchaseValidator purchaseValidator = new PurchaseValidator();

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            orderValidation.isValidOrderData(order);
            orderDAO.create(order);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Order> getAllOrdersByUserId(User user) throws ServiceException {
        try {
            return ((OrderDAO) orderDAO).findAllOrdersByUserId(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try {
            orderValidation.isValidOrderId(order);
            orderDAO.delete(order);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void checkoutCart(User user) throws ServiceException {
        try {
            List<Order> cart = ((OrderDAO) orderDAO).findAllOrdersByUserId(user);
            List<Drink> drinks = drinkDAO.getAll();
            user = ((UserDAO) userDAO).findById(user);
            purchaseValidator.isValidPurchase(cart, drinks, user);
            ((OrderDAO) orderDAO).checkoutCart(cart);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
