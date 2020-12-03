package com.tsybulko.validation;

import com.tsybulko.entity.Order;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ValidationException;

import java.util.List;

public class OrderValidation {

    private static final int MIN_ID = 1;

    public void isValidOrder(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isValidDrinkId(order);
        isUserNotNull(order);
        isValidUserId(order);
        isValidId(order);
    }

    public void isValidOrderData(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isValidDrinkId(order);
        isUserNotNull(order);
        isValidUserId(order);
    }

    public void isValidOrderId(Order order) throws ValidationException {
        isNotNull(order);
        isValidId(order);
    }

    public void isValidCart(List<Order> cart) throws ValidationException {
        for (Order order : cart) {
            isValidOrder(order);
        }
    }

    private void isNotNull(Order order) throws ValidationException {
        if (order == null) {
            throw new ValidationException(ExceptionsValue.NULL_ORDER.toString());
        }
    }

    private void isDrinkNotNull(Order order) throws ValidationException {
        if (order.getDrink() == null) {
            throw new ValidationException(ExceptionsValue.INVALID_ORDER_DRINK.toString());
        }
    }

    private void isValidDrinkId(Order order) throws ValidationException {
        if (order.getDrink().getId() < 1) {
            throw new ValidationException(ExceptionsValue.INVALID_ORDER_DRINK_ID.toString());
        }
    }

    private void isUserNotNull(Order order) throws ValidationException {
        if (order.getUser() == null) {
            throw new ValidationException(ExceptionsValue.INVALID_ORDER_USER.toString());
        }
    }

    private void isValidUserId(Order order) throws ValidationException {
        if (order.getUser().getId() < MIN_ID) {
            throw new ValidationException(ExceptionsValue.INVALID_ORDER_USER_ID.toString());
        }
    }

    private void isValidId(Order order) throws ValidationException {
        if ((order.getId() < MIN_ID)) {
            throw new ValidationException(ExceptionsValue.INVALID_ORDER_ID.toString());
        }
    }
}
