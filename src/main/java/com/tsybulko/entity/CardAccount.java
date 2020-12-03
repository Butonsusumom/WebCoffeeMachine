package com.tsybulko.entity;

import java.math.BigDecimal;

/**
 * Card Account.
 */
public class CardAccount {
    private int id;
    private String cardNumber;
    private BigDecimal amount;

    public CardAccount() {
    }

    public CardAccount(int id, String cardNumber, BigDecimal amount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccount that = (CardAccount) o;
        return id == that.id &&
                cardNumber.equals(that.cardNumber) &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CardAccount{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
