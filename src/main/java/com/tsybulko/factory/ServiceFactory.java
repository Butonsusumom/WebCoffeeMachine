package com.tsybulko.factory;

import com.tsybulko.service.DrinkService;
import com.tsybulko.service.OrderService;
import com.tsybulko.service.UserService;
import com.tsybulko.service.impl.DrinkServiceImpl;
import com.tsybulko.service.impl.OrderServiceImpl;
import com.tsybulko.service.impl.UserServiceImpl;

/**
 * Factory for services.
 */
public enum ServiceFactory {
    INSTANCE;

    private UserService userService = new UserServiceImpl();
    private DrinkService drinkService = new DrinkServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public DrinkService getDrinkService() {
        return drinkService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
