package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.ingredients.Ingredient;
import com.tsybulko.entity.items.Item;
import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;
import com.tsybulko.filters.DrinkFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("loginButton") != null) {
            if (USER_VALIDATOR.validateLoginIn(request.getParameter("login"), request.getParameter("password"))) {
                User user = SERVICE_FACTORY.getUserService().getUser(request.getParameter("login"));
                request.getSession().setAttribute("currentUser", user);
                if (user.isCustomer())
                    return customerForward(request);
                if (user.isAdmin())
                    return adminForward(request);
            } else {
                return COMMAND_FACTORY.getCommand("/machine/index").execute(request);
            }
        } else if ((request.getSession().getAttribute("currentUser")) != null) {
            if (USER_VALIDATOR.validateSession(request, Role.ADMIN)) {
                return adminForward(request);
            }
            if (USER_VALIDATOR.validateSession(request, Role.CUSTOMER)) {
                return customerForward(request);
            }
        }
        return COMMAND_FACTORY.getCommand("/machine/index").execute(request);
    }

    private String customerForward(HttpServletRequest req) {
        DrinkFilter.getDrinkFilterInstance().doFilter(req);
        List<Drink> drinks = SERVICE_FACTORY.getDrinkService().getRequestedDrinks((String) req.getSession().getAttribute("drinkType"),
                (String) req.getSession().getAttribute("milkAvailability"));
        req.setAttribute("sugarAvailable",
                SERVICE_FACTORY.getIngredientService().getIngredientByName("sugar").getQuantity() > 4);
        req.setAttribute("drinks", drinks);
        return "/pages/menu.jsp";
    }

    private String adminForward(HttpServletRequest req) {
        List<Ingredient> ingredients = SERVICE_FACTORY.getIngredientService().getIngredients();
        req.setAttribute("ingredients", ingredients);
        List<Item> items = SERVICE_FACTORY.getItemService().getItems();
        req.setAttribute("items", items);
        return "/pages/machine_settings.jsp";
    }
}
