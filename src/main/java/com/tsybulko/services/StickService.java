package com.tsybulko.services;

import com.tsybulko.dao.factory.impl.FactoryDAOImpl;
import com.tsybulko.entity.items.impl.Stick;

public class StickService {

    private static StickService stickServiceInstance;

    private StickService() {
    }

    public static StickService getStickServiceInstance() {
        if (stickServiceInstance == null) {
            synchronized (StickService.class) {
                if (stickServiceInstance == null) {
                    stickServiceInstance = new StickService();
                }
            }
        }
        return stickServiceInstance;
    }

    public int getSticksCount() {
        return FactoryDAOImpl.getFactoryDAOInstance().getItemDAO().getItemByName(Stick.DB_NAME).getCount();
    }
}
