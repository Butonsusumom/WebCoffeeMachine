package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.users.Role;

import javax.servlet.http.HttpServletRequest;

public class PersonalAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (USER_VALIDATOR.validateSession(request, Role.CUSTOMER)) {
            return "/pages/user_account.jsp";
        }
        return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
    }
}
