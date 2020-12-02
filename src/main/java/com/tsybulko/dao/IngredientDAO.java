package com.tsybulko.dao;

import com.tsybulko.entity.ingredients.Ingredient;

import java.util.List;

/**
 * DAO for operations with Ingredient objects
 */

public interface IngredientDAO {

    /**
     * @return Returns list of Ingredient objects from database
     */

    List<Ingredient> getAllIngredients();

    /**@param ingredientName - The name of the ingredient
     * @param quantity - The parameter sets the quantity of the ingredient
     * @return Return true if ingredient updated successfully
     */

    boolean updateIngredient(String ingredientName, int quantity);

    /**@param ingredientName - The name of ingredient
     * @return Returns the Ingredient object from database
     */

    Ingredient getIngredientByName(String ingredientName);
}
