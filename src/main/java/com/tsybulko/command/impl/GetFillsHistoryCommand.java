package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.Fill;
import com.tsybulko.entity.users.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetFillsHistoryCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!USER_VALIDATOR.validateSession(request, Role.ADMIN))
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        if (request.getParameter("ingrFillsType") != null || request.getParameter("itemfillsType") != null) {
            request.getSession().setAttribute("fillsType", request.getParameter("ingrFillsType") == null
                    ? "itemFillsType" : "ingrFillsType");
        } else {
            if (request.getSession().getAttribute("fillsType") == null)
                return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        }
        int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.valueOf(request.getParameter("currentPage"));
        List<Fill> fills = null;
        int fillsLength = 0;
        switch ((String)request.getSession().getAttribute("fillsType")){
            case "ingrFillsType":
                fills = SERVICE_FACTORY.getIngredientService().getIngredientFills((currentPage - 1) * 30);
                fillsLength = SERVICE_FACTORY.getIngredientService().getIngredientFillsCount();
                break;
            case "itemFillsType":
                fills = SERVICE_FACTORY.getItemService().getItemFills((currentPage - 1) * 30);
                fillsLength = SERVICE_FACTORY.getItemService().getItemFillsCount();
                break;
        }

        request.setAttribute("activated", currentPage);
        request.setAttribute("fills", fills);
        request.setAttribute("fillsLength", fillsLength);

        return "/pages/fillingHistory.jsp";
    }
}
