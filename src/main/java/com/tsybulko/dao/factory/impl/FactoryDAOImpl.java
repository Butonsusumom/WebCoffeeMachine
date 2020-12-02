package com.tsybulko.dao.factory.impl;

import com.tsybulko.dao.*;
import com.tsybulko.dao.factory.FactoryDAO;
import com.tsybulko.dao.impl.*;

public class FactoryDAOImpl implements FactoryDAO {

    private static FactoryDAO factoryDAOInstance;

    private FactoryDAOImpl(){}

    public static FactoryDAO getFactoryDAOInstance(){
        if(factoryDAOInstance == null){
            synchronized (FactoryDAOImpl.class){
                if(factoryDAOInstance == null)
                    factoryDAOInstance = new FactoryDAOImpl();
            }
        }
        return factoryDAOInstance;
    }

    @Override
    public IngredientDAO getIngredientDAO() {
        return IngredientDAOImpl.getIngredientDAOInstance();
    }

    @Override
    public ItemDAO getItemDAO() {
        return ItemDAOImpl.getItemDAOInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return UserDAOImpl.getUserDAOInstance();
    }

    @Override
    public FillDAO getFillDAO() { return FillDAOImpl.getFillDAOInstance(); }

    @Override
    public HumanDAO getHumanDAO() { return HumanDAOImpl.getHumanDAOInstance(); }

}
