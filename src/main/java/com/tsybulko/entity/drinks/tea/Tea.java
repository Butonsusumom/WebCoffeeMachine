package com.tsybulko.entity.drinks.tea;

import com.tsybulko.entity.drinks.Drink;
import com.tsybulko.entity.ingredients.Ingredient;

import java.math.BigDecimal;

public abstract class Tea implements Drink {
    private static final int WATER = 200;
    private static final BigDecimal PRICE = new BigDecimal(10);

    @Override
    public boolean isCoffee() {
        return false;
    }

    @Override
    public BigDecimal getPrice() {
        return PRICE;
    }


    @Override
    public boolean isTea() {
        return true;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getWater() {
        return WATER;
    }

    @Override
    public int getTotalSize() {
        int result = 0;
        for (Ingredient ingredient : getIngredients()) {
            result += ingredient.getQuantity();
        }
        return result;
    }

    public int getBlackTea() {
        return 0;
    }

    public int getGreenTea() {
        return 0;
    }
}
