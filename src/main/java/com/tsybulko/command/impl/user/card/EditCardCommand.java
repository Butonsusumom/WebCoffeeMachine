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

public class EditCardCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        try {
            User user = new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setCardAccount(
                            new CardAccountBuilder()
                                    .setId(Integer.parseInt(request.getParameter(JSPParameter.CARD_ID.getValue())))
                                    .setCardNumber(request.getParameter(JSPParameter.CARD_NUMBER.getValue()))
                                    .getResult())
                    .getResult();
            userService.updateCardInfoById(user);
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.EDIT_CARD_JSP.getValue();
            }
        }
    }
}
