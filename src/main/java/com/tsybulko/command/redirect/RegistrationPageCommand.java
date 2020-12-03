package com.tsybulko.command.redirect;

import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.REGISTRATION_JSR.getValue();
    }
}
