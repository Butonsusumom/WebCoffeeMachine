package com.tsybulko.command.impl;

import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrinkListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.DRINK_LIST.getValue(), ServiceFactory.INSTANCE.getDrinkService().getAll());
            return Pages.DRINK_LIST_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
