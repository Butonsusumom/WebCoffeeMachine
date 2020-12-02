package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.CoffeeMachine;
import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FillContainerCommand implements Command {
    private static CoffeeMachine coffeeMachine = CoffeeMachine.getCoffeeMachineInstance();
    private static Map<String, Integer> ingr = new HashMap<String, Integer>() {
        {
            put("water", coffeeMachine.getWaterMaxQuantity());
            put("milk", coffeeMachine.getMilkMaxQuantity());
            put("coffee", coffeeMachine.getCoffeeMaxQuantity());
            put("sugar", coffeeMachine.getSugarMaxQuantity());
            put("black_tea", coffeeMachine.getBlackTeaMaxQuantity());
            put("green_tea", coffeeMachine.getGreenTeaMaxQuantity());
        }
    };
    private static Map<String, Integer> items = new HashMap<String, Integer>() {
        {
            put("sticks", coffeeMachine.getSticksMaxCount());
            put("little_cups", coffeeMachine.getLittleCupsMaxCount());
            put("middle_cups", coffeeMachine.getMiddleCupsMaxCount());
            put("big_cups", coffeeMachine.getBigCupsMaxCount());
        }
    };

    @Override
    public String execute(HttpServletRequest request) {
        if (!USER_VALIDATOR.validateSession(request, Role.ADMIN))
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        User admin = (User) request.getSession().getAttribute("currentUser");
        String ingredient = null;
        String item = null;
        int quantity = 0;
        for (Map.Entry entry : ingr.entrySet()) {
            String key = (String) entry.getKey();
            if (request.getParameter(key) != null) {
                ingredient = key;
                quantity = (Integer) entry.getValue();
                break;
            }
        }
        for (Map.Entry entry : items.entrySet()) {
            String key = (String) entry.getKey();
            if (request.getParameter(key) != null) {
                item = key;
                quantity = (Integer) entry.getValue();
                break;
            }
        }
        if (ingredient != null) {
            if (SERVICE_FACTORY.getIngredientService().getIngredientByName(ingredient).filledInPercentage() < 6)
                SERVICE_FACTORY.getIngredientService().updateIngredient(ingredient, quantity, admin);
        }
        if (item != null) {
            if (SERVICE_FACTORY.getItemService().getItemByName(item).filledInPercentage() < 6)
                SERVICE_FACTORY.getItemService().updateItem(item, quantity, admin);
        }
        return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
    }
}
