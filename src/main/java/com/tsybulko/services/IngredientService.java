package com.tsybulko.services;

import com.tsybulko.dao.FillDAO;
import com.tsybulko.dao.IngredientDAO;
import com.tsybulko.dao.factory.impl.FactoryDAOImpl;
import com.tsybulko.entity.Fill;
import com.tsybulko.entity.ingredients.Ingredient;
import com.tsybulko.entity.users.User;

import java.util.List;

public class IngredientService {

    private static IngredientService ingredientServiceInstance;
    private IngredientDAO ingredientDAO = FactoryDAOImpl.getFactoryDAOInstance().getIngredientDAO();
    private FillDAO fillDAO = FactoryDAOImpl.getFactoryDAOInstance().getFillDAO();

    private IngredientService() {
    }

    public static IngredientService getIngredientServiceInstance() {
        if (ingredientServiceInstance == null) {
            synchronized (IngredientService.class) {
                if (ingredientServiceInstance == null)
                    ingredientServiceInstance = new IngredientService();
            }
        }
        return ingredientServiceInstance;
    }

    public List<Ingredient> getIngredients() {
        return ingredientDAO.getAllIngredients();
    }

    public void updateIngredient(String ingredientName, int quantity, User admin) {
        if (ingredientDAO.updateIngredient(ingredientName, quantity)) {
            fillDAO.noteIngredientFill(ingredientDAO.getIngredientByName(ingredientName), quantity, admin);
        }
    }

    public Ingredient getIngredientByName(String sugar) {
        if (sugar == null)
            return null;
        return ingredientDAO.getIngredientByName(sugar);
    }

    public void checkExpirationDate() {
        List<Ingredient> ingredients = ingredientDAO.getAllIngredients();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getExpirationDate().getTime() <= System.currentTimeMillis()) {
                ingredientDAO.updateIngredient(ingredient.getName(), 0);
            }
        }
    }

    public List<Fill> getIngredientFills(int skipCount) {
        return fillDAO.getIngredientFillsLimit(skipCount);
    }

    public Integer getIngredientFillsCount() {
        return fillDAO.getIngredientFillsLength();
    }
}
