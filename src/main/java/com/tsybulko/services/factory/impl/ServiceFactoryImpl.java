package com.tsybulko.services.factory.impl;

import com.tsybulko.services.*;
import com.tsybulko.services.factory.ServiceFactory;

public class ServiceFactoryImpl implements ServiceFactory {

    private static ServiceFactory serviceFactoryInstance;

    private ServiceFactoryImpl() {
    }

    public static ServiceFactory getServiceFactoryInstance() {
        if (serviceFactoryInstance == null) {
            synchronized (ServiceFactoryImpl.class) {
                if (serviceFactoryInstance == null)
                    serviceFactoryInstance = new ServiceFactoryImpl();
            }
        }
        return serviceFactoryInstance;
    }

    @Override
    public CupService getCupService() {
        return CupService.getCupServiceInstance();
    }

    @Override
    public DrinkService getDrinkService() {
        return DrinkService.getDrinkServiceInstance();
    }

    @Override
    public IngredientService getIngredientService() {
        return IngredientService.getIngredientServiceInstance();
    }

    @Override
    public ItemService getItemService() {
        return ItemService.getItemServiceInstance();
    }

    @Override
    public PasswordService getPasswordService() {
        return PasswordService.getPasswordServiceInstance();
    }

    @Override
    public StickService getStickService() {
        return StickService.getStickServiceInstance();
    }

    @Override
    public UserService getUserService() {
        return UserService.getUserServiceInstance();
    }
}
