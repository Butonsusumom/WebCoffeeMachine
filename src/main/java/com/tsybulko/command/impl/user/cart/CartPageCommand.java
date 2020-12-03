package com.tsybulko.command.impl.user.cart;

import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.Pages;
import com.tsybulko.entity.Order;
import com.tsybulko.entity.User;
import com.tsybulko.exception.ServiceException;
import com.tsybulko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CartPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User)request.getSession().getAttribute(Attribute.USER.getValue());
            List<Order> orderList = ServiceFactory.INSTANCE.getOrderService().getAllOrdersByUserId(user);
            request.getSession().setAttribute(Attribute.ORDER_LIST.getValue(), orderList);
            return Pages.CART_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
