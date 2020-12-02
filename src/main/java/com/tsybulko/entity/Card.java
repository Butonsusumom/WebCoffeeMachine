package com.tsybulko.entity;

import java.util.Date;

public class Card {
    private String cardNumber;
    private String secureCode;
    private Date expiryDate;

    public Card(String cardNumber, String secureCode, Date expiryDate){
        this.cardNumber = cardNumber;
        this.secureCode = secureCode;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public Date getExpiryDate() { return expiryDate; }
}
