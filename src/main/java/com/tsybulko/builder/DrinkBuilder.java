package com.tsybulko.builder;

import com.tsybulko.entity.Drink;
import com.tsybulko.entity.DrinkSize;

import java.math.BigDecimal;

/**
 * Drink builder.
 */

public class DrinkBuilder {
    /**
     * Id.
     */
    private int id;
    /**
     * Title.
     */
    private String title;
    /**
     * Price.
     */
    private BigDecimal price;
    /**
     * Drink size.
     */
    private DrinkSize drinkSize;
    /**
     * Serving number.
     */
    private int servingNumber;

    /**
     * Constructor without parameters.
     */
    public DrinkBuilder() {
    }

    /**
     * Setter.
     *
     * @param id entity id.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Setter.
     *
     * @param title entity title.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Setter.
     *
     * @param price entity price.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * Setter.
     *
     * @param drinkSize entity drink size.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
        return this;
    }

    /**
     * Setting drink size by drink size id.
     *
     * @param id @see DrinkSize id.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setDrinkSize(int id) {
        this.drinkSize = DrinkSize.getDrinkSizeById(id);
        return this;
    }

    /**
     * Setting drink size by drink size title.
     *
     * @param drinkSize @see DrinkSize title.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setDrinkSize(String drinkSize) {
        this.drinkSize = DrinkSize.valueOf(drinkSize);
        return this;
    }

    /**
     * Setter.
     *
     * @param servingNumber entity serving number.
     * @return @see DrinkBuilder
     */
    public DrinkBuilder setServingNumber(int servingNumber) {
        this.servingNumber = servingNumber;
        return this;
    }

    /**
     * Getter.
     *
     * @return @see DrinkBuilder with builded poles.
     */
    public Drink getResult() {
        return new Drink(id, title, price, drinkSize, servingNumber);
    }
}
