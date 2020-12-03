package com.tsybulko.command.impl;

import com.tsybulko.builder.UserBuilder;
import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    ServiceFactory.INSTANCE.getUserService()
                            .signIn(new UserBuilder()
                                    .setEmail(request.getParameter(JSPParameter.USER_EMAIL.getValue()))
                                    .setPassword(request.getParameter(JSPParameter.USER_PASSWORD.getValue()))
                                    .getResult()));
            return Pages.INDEX_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.SIGN_IN_JSP.getValue();
            }
        }
    }
}
