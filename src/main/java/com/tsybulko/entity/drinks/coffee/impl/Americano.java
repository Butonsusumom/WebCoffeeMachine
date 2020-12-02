package com.tsybulko.entity.drinks.coffee.impl;

import com.tsybulko.entity.drinks.coffee.Coffee;
import com.tsybulko.entity.ingredients.Ingredient;

import java.math.BigDecimal;

public class Americano extends Coffee {
    private static final int WATER = 120;
    private static final int COFFEE = 14;
    private static final int MILK = 0;
    private static final BigDecimal PRICE = new BigDecimal(12);
    private int sugar;

    public Americano() {
        sugar = REGULAR_SUGAR_COUNT;
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
    public int getCoffee() {
        return COFFEE;
    }

    @Override
    public int getMilk() {
        return MILK;
    }

    @Override
    public int getSugar() {
        return sugar;
    }

    @Override
    public BigDecimal getPrice() {
        return PRICE;
    }

    @Override
    public Ingredient[] getIngredients() {
        Ingredient[] result = {
                new Ingredient("water", WATER),
                new Ingredient("coffee", COFFEE),
                new Ingredient("milk", MILK),
                new Ingredient("sugar", sugar)};
        return result;
    }

    @Override
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
