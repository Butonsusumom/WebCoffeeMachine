package com.tsybulko.command.impl.admin.users;

import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> userList = ServiceFactory.INSTANCE.getUserService().getAll();
            request.setAttribute(Attribute.USER_LIST.getValue(), userList);
            return Pages.USER_LIST_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
