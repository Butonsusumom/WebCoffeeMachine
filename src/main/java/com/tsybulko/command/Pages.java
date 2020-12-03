package com.tsybulko.command;

/**
 * Represent URL for pages.
 */
public enum Pages {
    INDEX_JSP("page/index.jsp", "INDEX_PAGE"),
    REGISTRATION_JSR("page/registration.jsp","REGISTRATION_PAGE"),
    ERROR_500_JSP("page/error500.jsp", "500"),
    SIGN_IN_JSP("page/sign_in.jsp", "SIGN_IN_PAGE"),
    DRINK_LIST_JSP("page/drinks.jsp", "DRINK_LIST"),

    USER_LIST_JSP("page/admin/user_list.jsp", "USER_LIST"),
    USER_EDIT_JSP("page/admin/user_edit.jsp", "USER_EDIT"),
    DRINK_MANAGEMENT_JSP("page/admin/drink_management.jsp", "DRINK_MANAGEMENT"),
    DRINK_ADDING_JSP("page/admin/add_drink.jsp", "ADD_DRINK_PAGE"),
    DRINK_ADDING_SERVINGS_JSP("page/admin/add_servings.jsp", "ADD_SERVINGS_PAGE"),

    USER_PROFILE_JSP("page/user/user_profile.jsp", "USER_PROFILE"),
    EDIT_PROFILE_JSP("page/user/edit_profile.jsp", "EDIT_PROFILE_PAGE"),
    ADD_CARD_JSP("page/user/add_card.jsp", "ADD_CARD_PAGE"),
    EDIT_CARD_JSP("page/user/edit_card.jsp", "EDIT_CARD_PAGE"),
    ADD_MONEY_JSP("page/user/add_money.jsp", "ADD_MONEY_PAGE"),
    CART_JSP("page/user/cart.jsp", "CART_PAGE");

    private String value;
    private String commandName;

    Pages(String value, String commandName) {
        this.value = value;
        this.commandName = commandName;
    }

    public String getValue() {
        return value;
    }

    public String getCommandName() {
        return commandName;
    }

    public static String getCommandNameByPath(String path) {
        for (Pages page : Pages.values()) {
            if (page.getValue().equals(path)) {
                return page.getCommandName();
            }
        }
        return ERROR_500_JSP.getCommandName();
    }
}
