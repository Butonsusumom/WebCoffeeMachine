package com.tsybulko.entity;

import java.math.BigDecimal;

/**
 * Drink.
 */
public class Drink {
    private int id;
    private String title;
    private BigDecimal price;
    private DrinkSize drinkSize;
    private int servingNumber;

    public Drink() {
    }

    public Drink(int id, String title, BigDecimal price, DrinkSize drinkSize, int servingNumber) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.drinkSize = drinkSize;
        this.servingNumber = servingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DrinkSize getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
    }

    public int getServingNumber() {
        return servingNumber;
    }

    public void setServingNumber(int servingNumber) {
        this.servingNumber = servingNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return id == drink.id &&
                servingNumber == drink.servingNumber &&
                title.equals(drink.title) &&
                price.equals(drink.price) &&
                drinkSize == drink.drinkSize;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (drinkSize != null ? drinkSize.hashCode() : 0);
        result = 31 * result + servingNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", drinkSize=" + drinkSize +
                ", servingNumber=" + servingNumber +
                '}';
    }
}
