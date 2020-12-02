package com.tsybulko.controller;

import com.tsybulko.command.Command;
import com.tsybulko.command.factory.CommandFactory;
import com.tsybulko.command.factory.impl.CommandFactoryImpl;
import com.tsybulko.filters.Localization;
import com.tsybulko.filters.UTF8Encoder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Dispatcher.class);
    private static CommandFactory commandFactory = CommandFactoryImpl.getCommandFactoryImplInstance();
    private Localization localization = Localization.getLocalizationInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) {
        try {
            UTF8Encoder.getUtf8EncoderInstance().encode(request, response);
            localization.checkLocale(request);
            Command command = commandFactory.getCommand(request.getRequestURI());
            if (command != null) {
                String path = command.execute(request);
                if (path.equals("/pages/ready.jsp") || path.equals("/pages/account_replenished.jsp"))
                    response.sendRedirect(path);
                else forward(path, request, response);
            } else {
                forward(commandFactory.getCommand("/machine/index").execute(request), request, response);
            }
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, "Exception", e);
            e.printStackTrace();
            forward("/pages/error.jsp", request, response);
        }
    }

    public void forward(String to, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(to);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.DEBUG, "Exception", e);
            e.printStackTrace();
        }
    }
}
