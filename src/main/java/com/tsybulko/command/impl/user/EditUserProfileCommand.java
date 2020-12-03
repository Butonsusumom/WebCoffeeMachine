package com.tsybulko.command.impl.user;

import com.tsybulko.builder.UserBuilder;
import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;
import com.tsybulko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserProfileCommand implements Command {
    private UserService userService = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.updateUserInfoById(new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setName(request.getParameter(JSPParameter.USER_NAME.getValue()))
                    .setEmail(request.getParameter(JSPParameter.USER_EMAIL.getValue()))
                    .setPassword(request.getParameter(JSPParameter.USER_PASSWORD.getValue()))
                    .getResult());
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.EDIT_PROFILE_JSP.getValue();
            }
        }
    }
}
