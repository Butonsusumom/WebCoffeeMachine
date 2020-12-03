package com.tsybulko.command.redirect.admin;

import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDrinkPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.DRINK_ADDING_JSP.getValue();
    }
}
