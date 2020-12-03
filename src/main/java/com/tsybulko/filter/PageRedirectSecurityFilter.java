package com.tsybulko.filter;

import com.tsybulko.command.JSPParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security filter. Restricts access without command.
 */
@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = {"/page/*"})
public class PageRedirectSecurityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getParameter(JSPParameter.COMMAND.getValue()) == null) {
            resp.sendRedirect(req.getContextPath() + "/coffee_machine?command=INDEX_PAGE");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

