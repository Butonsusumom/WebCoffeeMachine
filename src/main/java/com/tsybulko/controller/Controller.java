package com.tsybulko.controller;

import com.tsybulko.command.Attribute;
import com.tsybulko.command.Command;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;
import com.tsybulko.exception.ConnectionPoolException;
import com.tsybulko.factory.CommandFactory;
import com.tsybulko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Application servlet. Runner.
 */
@WebServlet("/coffee_machine")
public class Controller extends HttpServlet {
    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(Controller.class);
    /**
     * Command Factory.
     */
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.INSTANCE;
    /**
     * Redirect URL template.
     */
    private static final String URL = "%s%s?command=%s";

    /**
     * Initialize project and connection pool.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.INSTANCE.init();
        } catch (ConnectionPoolException ex) {
            logger.fatal(ex);
        }
    }

    /**
     * Close connection pool.
     */
    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }

    /**
     * Get.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(processRequest(req, resp)).forward(req, resp);
    }

    /**
     * Post.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextPage = processRequest(req, resp);
        String next = Pages.getCommandNameByPath(nextPage);
        if (next.equals(Pages.ERROR_500_JSP.getCommandName())) {
            resp.sendError(Integer.parseInt(next));
        } else {
            if (req.getAttribute(Attribute.ERROR_MESSAGE.getValue()) != null) {
                req.getRequestDispatcher(nextPage).forward(req, resp);
            } else {
                resp.sendRedirect(String.format(URL, req.getContextPath(), req.getServletPath(), next));
            }
        }
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Command command = COMMAND_FACTORY.getCommand(req.getParameter(JSPParameter.COMMAND.getValue()));
        return command.execute(req, resp);
    }
}

