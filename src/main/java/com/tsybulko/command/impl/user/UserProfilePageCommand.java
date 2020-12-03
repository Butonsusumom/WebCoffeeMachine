package com.tsybulko.command.impl.user;

import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfilePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Attribute.USER.getValue());
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    ServiceFactory.INSTANCE.getUserService().findById(user));
            request.setAttribute(Attribute.HISTORY.getValue(),
                    ServiceFactory.INSTANCE.getDrinkService().getPurchaseHistoryByUserId(user));
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
