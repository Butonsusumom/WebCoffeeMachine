package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.drinks.coffee.impl.Americano;
import com.tsybulko.entity.drinks.coffee.impl.Cappuccino;
import com.tsybulko.entity.drinks.coffee.impl.Espresso;
import com.tsybulko.entity.drinks.tea.impl.BlackTea;
import com.tsybulko.entity.drinks.tea.impl.GreenTea;
import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MakeDrinkCommand implements Command {

    private static List<Drink> drinks = new ArrayList<Drink>() {
        {
            add(new Espresso());
            add(new Americano());
            add(new Cappuccino());
            add(new com.tsybulko.entity.drinks.coffee.impl.Latte());
            add(new BlackTea());
            add(new GreenTea());
        }
    };

    @Override
    public String execute(HttpServletRequest request) {
        int countOfSugar = request.getParameter("sugar") == null ? 0 : Integer.valueOf(request.getParameter("sugar"));
        if (!USER_VALIDATOR.validateSession(request, Role.CUSTOMER))
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        User user = (User) request.getSession().getAttribute("currentUser");
        Drink selectedDrink = null;
        for (Drink drink : drinks) {
            String drinkValue = drink.getName() + " " + drink.getPrice().toString() + " UAH";
            if (drinkValue.equals(request.getParameter("selectedDrink")))
                selectedDrink = drink;
        }
        if (selectedDrink == null)
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        selectedDrink.setSugar(countOfSugar * Drink.GRAM_OF_SUGAR_IN_SPOON);
        if (user.getBalance().compareTo(selectedDrink.getPrice()) >= 0 && SERVICE_FACTORY.getDrinkService().makeDrink(selectedDrink, user)) {
            request.getSession().setAttribute("drink", selectedDrink);
            USER_VALIDATOR.validateSession(request, Role.CUSTOMER);
            return "/pages/ready.jsp";
        } else if (user.getBalance().compareTo(selectedDrink.getPrice()) < 0) {
            return "/pages/not_enough_money.jsp";
        }
        return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
    }

}
