package com.tsybulko.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command interface.
 */

public interface Command {
    /**
     * Execute command.
     * @param request
     * @param response
     * @return next URL for next page
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
