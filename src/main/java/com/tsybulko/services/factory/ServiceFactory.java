package com.tsybulko.services.factory;

import com.tsybulko.services.*;

public interface ServiceFactory {

    CupService getCupService();

    DrinkService getDrinkService();

    IngredientService getIngredientService();

    ItemService getItemService();

    PasswordService getPasswordService();

    StickService getStickService();

    UserService getUserService();
}
