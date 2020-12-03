package com.tsybulko.command.redirect.user;

import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCardPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.ADD_CARD_JSP.getValue();
    }
}
