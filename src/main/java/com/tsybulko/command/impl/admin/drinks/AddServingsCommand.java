package com.tsybulko.command.impl.admin.drinks;

import com.tsybulko.builder.DrinkBuilder;
import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServingsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.INSTANCE.getDrinkService()
                    .update(new DrinkBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                            .setServingNumber(Integer.parseInt(request.getParameter(JSPParameter.DRINK_SERVING_NUMBER.getValue())))
                            .getResult());
            return Pages.DRINK_MANAGEMENT_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.DRINK_ADDING_SERVINGS_JSP.getValue();
            }
        }
    }
}
