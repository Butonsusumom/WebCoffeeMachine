package com.tsybulko.validation;

import com.tsybulko.entity.CardAccount;
import com.tsybulko.exception.ExceptionsValue;
import com.tsybulko.exception.ValidationException;

import java.math.BigDecimal;


public class CardAccountValidator {

    private static final String CARD_NUMBER_REGEX = "^[0-9]{16}$";
    private static final BigDecimal MIN_AMOUNT = new BigDecimal(0);
    private static final int MIN_ID = 1;

    public void isValidCardAccountData(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidNumber(cardAccount);
    }

    public void isValidCardAccountIdAndAmount(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
        isValidAmount(cardAccount);
    }

    public void isValidCardAccountIdAndNumber(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
        isValidNumber(cardAccount);
    }

    public void isValidCardAccountId(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
    }

    private void isNotNull(CardAccount cardAccount) throws ValidationException {
        if (cardAccount == null) {
            throw new ValidationException(ExceptionsValue.NULL_CARD.toString());
        }
    }

    private void isValidId(CardAccount cardAccount) throws ValidationException {
        if (cardAccount.getId() < MIN_ID) {
            throw new ValidationException(ExceptionsValue.INVALID_CARD_ID.toString());
        }
    }

    private void isValidNumber(CardAccount cardAccount) throws ValidationException {
        if (!cardAccount.getCardNumber().matches(CARD_NUMBER_REGEX)) {
            throw new ValidationException(ExceptionsValue.INVALID_CARD_NUMBER.toString());
        }
    }

    private void isValidAmount(CardAccount cardAccount) throws ValidationException {
        if (cardAccount.getAmount().compareTo(MIN_AMOUNT) < 1) {
            throw new ValidationException(ExceptionsValue.INVALID_AMOUNT.toString());
        }
    }
}
