package com.tsybulko.entity.drinks.tea.impl;

import com.tsybulko.entity.drinks.tea.Tea;
import com.tsybulko.entity.ingredients.Ingredient;

public class GreenTea extends Tea {

    private static final int GREEN_TEA = 10;
    private int sugar;

    public GreenTea() {
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
                new Ingredient("black_tea", GREEN_TEA),
                new Ingredient("sugar", getSugar())};
        return result;
    }

    @Override
    public String getName() {
        return "Green tea";
    }

    @Override
    public int getGreenTea() {
        return GREEN_TEA;
    }

    @Override
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
