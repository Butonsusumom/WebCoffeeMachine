package com.tsybulko.dao.factory;

import com.tsybulko.dao.*;

public interface FactoryDAO {

    IngredientDAO getIngredientDAO();

    ItemDAO getItemDAO();

    UserDAO getUserDAO();

    FillDAO getFillDAO();

    HumanDAO getHumanDAO();
}
