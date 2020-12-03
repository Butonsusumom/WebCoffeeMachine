package com.tsybulko.validation;

import com.tsybulko.entity.Drink;
import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ValidationException;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseValidator {

    private static final BigDecimal MIN_CARD_AMOUNT = new BigDecimal(0);
    private static final int MIN_SERVING_NUMBER = 0;

    public void isValidPurchase(List<Order> cart, List<Drink> drinks, User user) throws ValidationException {
        isValidCardAccount(user);
        isEnoughUserMoneyAmount(user, getCartAmount(cart));
        isEnoughServingNumber(subtractCartOrdersFromServingNumbers(cart, drinks));
    }

    private void isEnoughUserMoneyAmount(User user, BigDecimal cartAmount) throws ValidationException {
        if (user.getCardAccount().getAmount().subtract(cartAmount).compareTo(MIN_CARD_AMOUNT) < 0) {
            throw new ValidationException(ExceptionsValue.NOT_ENOUGH_MONEY.toString());
        }
    }

    private BigDecimal getCartAmount(List<Order> cart) {
        BigDecimal amount = new BigDecimal(0);
        for (Order order : cart) {
            amount = amount.add(order.getDrink().getPrice());
        }
        return amount;
    }

    private void isEnoughServingNumber(List<Drink> drinks) throws ValidationException {
        for (Drink drink : drinks) {
            if (drink.getServingNumber() < MIN_SERVING_NUMBER) {
                throw new ValidationException(ExceptionsValue.NOT_ENOUGH_SERVING_NUMBER.toString());
            }
        }
    }

    private List<Drink> subtractCartOrdersFromServingNumbers(List<Order> cart, List<Drink> drinks) {
        for (Order order : cart) {
            for (Drink drink : drinks) {
                if (order.getDrink().getId() == drink.getId()) {
                    drink.setServingNumber(drink.getServingNumber() - 1);
                    break;
                }
            }
        }
        return drinks;
    }

    private void isValidCardAccount(User user) throws ValidationException {
        if (user.getCardAccount().getCardNumber() == null) {
            throw new ValidationException(ExceptionsValue.NO_ATTACHED_CARD.toString());
        }
    }
}
