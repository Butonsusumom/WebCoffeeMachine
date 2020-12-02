package com.tsybulko.filters;

import javax.servlet.http.HttpServletRequest;

public class Localization {

    private static Localization localizationInstance;

    private Localization() {
    }

    public static Localization getLocalizationInstance() {
        if (localizationInstance == null) {
            synchronized (Localization.class) {
                if (localizationInstance == null)
                    localizationInstance = new Localization();
            }
        }
        return localizationInstance;
    }

    public void checkLocale(HttpServletRequest request) {
        if (request.getParameter("locale") != null)
            request.getSession().setAttribute("locale", request.getParameter("locale"));
        if (request.getSession().getAttribute("locale") == null)
            request.getSession().setAttribute("locale", "ua");
    }
}
