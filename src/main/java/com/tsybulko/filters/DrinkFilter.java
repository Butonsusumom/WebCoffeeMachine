package com.tsybulko.filters;

import javax.servlet.http.HttpServletRequest;

public class DrinkFilter {

    private static DrinkFilter drinkFilterInstance;

    private DrinkFilter() {
    }

    public static DrinkFilter getDrinkFilterInstance() {
        if (drinkFilterInstance == null) {
            synchronized (DrinkFilter.class) {
                if (drinkFilterInstance == null)
                    drinkFilterInstance = new DrinkFilter();
            }
        }
        return drinkFilterInstance;
    }

    public void doFilter(HttpServletRequest req) {
        if (req.getParameter("milkAvailability") != null)
            req.getSession().setAttribute("milkAvailability", req.getParameter("milkAvailability"));
        else if (req.getSession().getAttribute("milkAvailability") == null)
            req.getSession().setAttribute("milkAvailability", "allDrinks");
        if (req.getParameter("drinkType") != null)
            req.getSession().setAttribute("drinkType", req.getParameter("drinkType"));
        else if (req.getSession().getAttribute("drinkType") == null)
            req.getSession().setAttribute("drinkType", "allDrinks");
    }
}
