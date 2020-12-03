package com.tsybulko.exception;

/**
 * Enum represent exception messages.
 */
public enum ExceptionsValue {
    NULL_USER("User is null"),
    INVALID_USER_NAME("Invalid user name"),
    INVALID_USER_EMAIL("Invalid user email"),
    INVALID_USER_PASSWORD("Invalid user password"),
    INVALID_USER_ID("Invalid user id"),
    BLOCKED_USER("User was blocked by admin"),

    NULL_CARD("Card account is null"),
    INVALID_CARD_ID("Card account id invalid"),
    INVALID_CARD_NUMBER("Invalid card number"),
    INVALID_AMOUNT("Invalid amount"),

    NULL_DRINK("Drink is null"),
    INVALID_DRINK_TITLE("Invalid drink title"),
    INVALID_DRINK_PRICE("Invalid drink price"),
    INVALID_DRINK_ID("Invalid drink id"),
    INVALID_DRINK_SERVING_NUMBER("Invalid drink serving number"),

    NULL_ORDER("Invalid order, order is null"),
    INVALID_ORDER_DRINK("Invalid drink, drink is null"),
    INVALID_ORDER_DRINK_ID("Invalid drink id"),
    INVALID_ORDER_USER("Invalid user, user is null"),
    INVALID_ORDER_USER_ID("Invalid user id"),
    INVALID_ORDER_ID("Invalid order id"),

    NOT_ENOUGH_MONEY("Not enough money to get purchase"),
    NOT_ENOUGH_SERVING_NUMBER("Not enough serving number to get purchase"),
    NO_ATTACHED_CARD("No attached card"),
    BOOKED_EMAIL("Email already taken"),
    INCORRECT_SIGN_IN_DATA("Incorrect email or password"),
    SERVER_ERROR("Internal error"),
    USER_CARD_MISMATCHING_EXCEPTION("Invalid card data, user card mismatch with the entered card"),
    NOT_EXIST_DRINK("Finding drink doesn't exist");


    private String value;

    ExceptionsValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
