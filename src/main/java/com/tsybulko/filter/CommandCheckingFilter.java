package com.tsybulko.filter;

import com.tsybulko.command.CommandParameter;
import com.tsybulko.command.JSPParameter;
import com.tsybulko.command.Pages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Security filter. Checks existed commands.
 */
@WebFilter(filterName = "CommandCheckingFilter", urlPatterns = {"/coffee_machine"})
public class CommandCheckingFilter implements Filter {

    private static final Set<String> COMMANDS = Arrays.stream(CommandParameter.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter(JSPParameter.COMMAND.getValue());
        if (command == null || !COMMANDS.contains(command)) {
            req.getRequestDispatcher(Pages.INDEX_JSP.getValue()).forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

