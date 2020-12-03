package com.tsybulko.command;

/**
 * Represent request attribute names.
 */
public enum Attribute {
    ERROR_MESSAGE("errorMessage"),
    USER_PROFILE("userProfile"),
    USER("user"),
    USER_LIST("userList"),
    DRINK_LIST("drinkList"),
    DRINK("drink"),
    ORDER_LIST("orderList"),
    LOCALE("locale"),
    LANGUAGE("language"),
    HISTORY("history");

    private String value;

    Attribute(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
