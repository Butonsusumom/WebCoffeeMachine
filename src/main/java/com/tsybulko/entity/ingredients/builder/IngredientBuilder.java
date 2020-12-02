package com.tsybulko.entity.ingredients.builder;

import com.tsybulko.entity.ingredients.Ingredient;

import java.sql.Date;

public class IngredientBuilder {
    private String name;
    private int quantity;
    private int id;
    private Date expirationDate;

    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IngredientBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public IngredientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public IngredientBuilder setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Ingredient build(){
        return  new Ingredient(name, quantity, id, expirationDate);
    }
}
