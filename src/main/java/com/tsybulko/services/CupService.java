package com.tsybulko.services;

import com.tsybulko.dao.ItemDAO;
import com.tsybulko.dao.factory.impl.FactoryDAOImpl;
import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.items.impl.BigCup;
import com.tsybulko.entity.items.impl.LittleCup;
import com.tsybulko.entity.items.impl.MiddleCup;

public class CupService {
    private static CupService cupServiceInstance;
    private static ItemDAO itemDAO = FactoryDAOImpl.getFactoryDAOInstance().getItemDAO();

    private CupService() {
    }

    public static CupService getCupServiceInstance() {
        if (cupServiceInstance == null) {
            synchronized (CupService.class) {
                if (cupServiceInstance == null)
                    cupServiceInstance = new CupService();
            }
        }
        return cupServiceInstance;
    }

    public String chooseCup4Drink(Drink drink) {
        String cup = null;
        if (LittleCup.SIZE >= drink.getTotalSize()
                && itemDAO.getItemByName(LittleCup.DB_NAME).getCount() > 0) {
            cup = LittleCup.DB_NAME;
        } else if (MiddleCup.SIZE >= drink.getTotalSize()
                && itemDAO.getItemByName(MiddleCup.DB_NAME).getCount() > 0) {
            cup = MiddleCup.DB_NAME;
        } else if (BigCup.SIZE >= drink.getTotalSize()
                && itemDAO.getItemByName(BigCup.DB_NAME).getCount() > 0) {
            cup = BigCup.DB_NAME;
        }
        return cup;
    }

    public int getCupCount(String cupName) {
        if (cupName == null)
            return 0;
        return itemDAO.getItemByName(cupName).getCount();
    }
}
