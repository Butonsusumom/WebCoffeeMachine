package com.tsybulko.entity.drinks.tea.impl;

import com.tsybulko.entity.drinks.tea.Tea;
import com.tsybulko.entity.ingredients.Ingredient;

public class BlackTea extends Tea {
    private static final int BLACK_TEA = 10;
    private int sugar;

    public BlackTea() {
        sugar = REGULAR_SUGAR_COUNT;
    }

    @Override
    public int getSugar() {
        return sugar;
    }

    @Override
    public Ingredient[] getIngredients() {
        Ingredient[] result = {
                new Ingredient("water", getWater()),
                new Ingredient("black_tea", BLACK_TEA),
                new Ingredient("sugar", getSugar())};
        return result;
    }

    @Override
    public String getName() {
        return "Black tea";
    }

    @Override
    public int getBlackTea() {
        return BLACK_TEA;
    }

    @Override
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
