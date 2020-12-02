package com.tsybulko.entity.drinks.coffee;

import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.ingredients.Ingredient;

public abstract class Coffee implements Drink {
    public abstract int getCoffee();
    public abstract int getMilk();

    @Override
    public boolean isCoffee() {
        return true;
    }

    @Override
    public boolean isTea() {
        return false;
    }

    @Override
    public int getTotalSize() {
        int result = 0;
        for(Ingredient ingredient : getIngredients()){
            result += ingredient.getQuantity();
        }
        return result;
    }
}
