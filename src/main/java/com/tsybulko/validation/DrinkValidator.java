package com.tsybulko.validation;

import com.tsybulko.entity.Drink;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ValidationException;

import java.math.BigDecimal;

public class DrinkValidator {

    private static final String DRINK_TITLE_REGEX = "^[a-zA-Zа-яА-Я]{1,40}$";
    private static final BigDecimal MIN_PRICE = new BigDecimal(0);
    private static final int MIN_ID = 1;
    private static final int MIN_SERVING_NUMBER = 0;

    public void isValidDrink(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
        isValidTitle(drink);
        isValidPrice(drink);
        isValidServingNumber(drink);
    }

    public void isValidDrinkId(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
    }

    public void isValidDrinkData(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidTitle(drink);
        isValidPrice(drink);
        isValidServingNumber(drink);
    }

    public void isValidIdAndServingNumber(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
        isValidServingNumber(drink);
    }

    private void isNotNull(Drink drink) throws ValidationException {
        if (drink == null) {
            throw new ValidationException(ExceptionsValue.NULL_DRINK.toString());
        }
    }

    private void isValidTitle(Drink drink) throws ValidationException {
        if (!drink.getTitle().matches(DRINK_TITLE_REGEX)) {
            throw new ValidationException(ExceptionsValue.INVALID_DRINK_TITLE.toString());
        }
    }

    private void isValidPrice(Drink drink) throws ValidationException {
        if (drink.getPrice().compareTo(MIN_PRICE) < 1) {
            throw new ValidationException(ExceptionsValue.INVALID_DRINK_PRICE.toString());
        }
    }

    private void isValidId(Drink drink) throws ValidationException {
        if (drink.getId() < MIN_ID) {
            throw new ValidationException(ExceptionsValue.INVALID_DRINK_ID.toString());
        }
    }

    private void isValidServingNumber(Drink drink) throws ValidationException {
        if (drink.getServingNumber() < MIN_SERVING_NUMBER) {
            throw new ValidationException(ExceptionsValue.INVALID_DRINK_SERVING_NUMBER.toString());
        }
    }
}
