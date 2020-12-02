package com.tsybulko.entity.drinks;

import com.tsybulko.entity.ingredients.Ingredient;

import java.math.BigDecimal;

public interface Drink {
    int GRAM_OF_SUGAR_IN_SPOON = 5;
    int REGULAR_SUGAR_COUNT = 10;

    String getName();
    int getWater();
    int getSugar();
    BigDecimal getPrice();
    Ingredient[] getIngredients();
    int getTotalSize();
    boolean isCoffee();
    boolean isTea();
    void setSugar(int sugar);
}
