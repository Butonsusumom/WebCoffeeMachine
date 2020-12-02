package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.users.buider.UserBuilder;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("registrationButton") != null)
            return "/pages/registration.jsp";

        if (SERVICE_FACTORY.getUserService().registerUser(
                new UserBuilder().
                        setLogin(request.getParameter("login")).
                        setPassword(request.getParameter("password")).
                        setFirstName(request.getParameter("firstName")).
                        setLastName(request.getParameter("lastName")).
                        setMiddleName(request.getParameter("middleName")).
                        build(), request)) {
            request.getSession().setAttribute("currentUser", SERVICE_FACTORY.getUserService().getUser(request.getParameter("login")));
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        }
        return "/pages/registration.jsp";
    }
}
