package com.tsybulko.command.impl.user.card;

import com.tsybulko.builder.CardAccountBuilder;
import com.tsybulko.builder.UserBuilder;
import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.entity.User;
import com.tsybulko.exception.DAOException;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;
import com.tsybulko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddCardCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        try {
            User user = new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setCardAccount(
                            new CardAccountBuilder()
                            .setCardNumber(request.getParameter(JSPParameter.CARD_NUMBER.getValue()))
                            .setAmount(BigDecimal.valueOf(Double.parseDouble(request.getParameter(JSPParameter.CARD_AMOUNT.getValue()))))
                            .getResult())
                    .getResult();
            userService.attachCardToUserById(user);
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.ADD_CARD_JSP.getValue();
            }
        }
    }
}
