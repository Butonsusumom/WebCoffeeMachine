package com.tsybulko.command.impl;

import com.tsybulko.command.Command;
import com.tsybulko.entity.Card;
import com.tsybulko.entity.users.Role;
import com.tsybulko.entity.users.User;
import com.tsybulko.validators.CardValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplenishAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!USER_VALIDATOR.validateSession(request, Role.CUSTOMER))
            return COMMAND_FACTORY.getCommand("/machine/login").execute(request);
        if (request.getParameter("submit") != null
                && CardValidator.validateExpiryDate(request.getParameter("month"), request.getParameter("year"))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyy");
            Date expiryDate = null;
            try {
                expiryDate = simpleDateFormat.parse(request.getParameter("month") + request.getParameter("year"));
            } catch (ParseException e) {}
            Card card = new Card(request.getParameter("cardNumber"), request.getParameter("secureCode"), expiryDate);
            if (CardValidator.validateCard(card, request.getParameter("amount"))) {
                User user = (User) request.getSession().getAttribute("currentUser");
                BigDecimal balance = user.getBalance().add(new BigDecimal(request.getParameter("amount")));
                SERVICE_FACTORY.getUserService().updateBalance(balance, user.getLogin());
                request.getSession().setAttribute("currentUser", SERVICE_FACTORY.getUserService().getUser(user.getLogin()));
                request.getSession().setAttribute("value", request.getParameter("amount"));
            }
        return "/pages/account_replenished.jsp";
        }
        return "/machine/personalAccount";
    }
}
