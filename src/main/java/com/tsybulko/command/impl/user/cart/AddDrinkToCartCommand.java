package com.tsybulko.command.impl.user.cart;

import com.tsybulko.builder.DrinkBuilder;
import com.tsybulko.builder.OrderBuilder;
import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDrinkToCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.INSTANCE.getOrderService().addOrder(
                    new OrderBuilder()
                            .setDrink(new DrinkBuilder()
                                    .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                                    .getResult())
                            .setUser((User) request.getSession().getAttribute(Attribute.USER.getValue()))
                            .getResult());
            return Pages.DRINK_LIST_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
