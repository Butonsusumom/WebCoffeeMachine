package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.services.IngredientController;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command {
    private static boolean controllerRunning = false;

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().removeAttribute("currentUser");
        if (!controllerRunning) {
            synchronized (this.getClass()) {
                if (!controllerRunning) {
                    controllerRunning = true;
                    new Thread(new IngredientController()).start();
                }
            }
        }
        return "/pages/index.jsp";
    }
}
