package com.tsybulko.builder;

import com.tsybulko.entity.CardAccount;

import java.math.BigDecimal;

/**
 * CardAccount builder.
 */

public class CardAccountBuilder {
    /**
     * Id.
     */
    private int id;
    /**
     * Card Number.
     */
    private String cardNumber;
    /**
     * Amount.
     */
    private BigDecimal amount;

    /**
     * Constructor without parameters.
     */
    public CardAccountBuilder() {
    }

    /**
     * Setter.
     *
     * @param id entity id.
     * @return @see CardAccountBuilder
     */
    public CardAccountBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Setter.
     *
     * @param cardNumber entity cardNumber.
     * @return @see CardAccountBuilder
     */
    public CardAccountBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    /**
     * Setter.
     *
     * @param amount entity amount.
     * @return @see CardAccountBuilder
     */
    public CardAccountBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Getter.
     *
     * @return @see CardAccountBuilder with builded poles.
     */
    public CardAccount getResult() {
        return new CardAccount(id, cardNumber, amount);
    }
}
