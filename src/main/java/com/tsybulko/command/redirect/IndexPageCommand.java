package com.tsybulko.command.redirect;

import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.INDEX_JSP.getValue();
    }
}
