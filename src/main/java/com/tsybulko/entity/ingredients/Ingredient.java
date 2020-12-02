package com.tsybulko.entity.ingredients;

import com.tsybulko.entity.CoffeeMachine;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Ingredient {
    private static Properties properties = new Properties();
    private String name;
    private int quantity;
    private int id;
    private Date expirationDate;
    private static CoffeeMachine coffeeMachine = CoffeeMachine.getCoffeeMachineInstance();

    public Ingredient(String name, int quantity) {
        try {
            properties.load(getClass().getResourceAsStream("/expiration_date.properties"));
        } catch (IOException e) { e.printStackTrace(); }
        this.name = name;
        this.quantity = quantity;
    }

    public Ingredient(String name, int quantity, int id, Date expirationDate) {
        try {
            properties.load(getClass().getResourceAsStream("/expiration_date.properties"));
        } catch (IOException e) { e.printStackTrace(); }
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public String getCamelCaseName() {
        return (name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase()).replaceAll("_", " ");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    public int getMaxQuantity() {
        switch (name) {
            case "milk":
                return coffeeMachine.getMilkMaxQuantity();
            case "coffee":
                return coffeeMachine.getCoffeeMaxQuantity();
            case "water":
                return coffeeMachine.getWaterMaxQuantity();
            case "sugar":
                return coffeeMachine.getSugarMaxQuantity();
            case "black_tea":
                return coffeeMachine.getBlackTeaMaxQuantity();
            case "green_tea":
                return coffeeMachine.getGreenTeaMaxQuantity();
        }
        return -1;
    }

    public int filledInPercentage() {
        return (int) (((double) quantity / getMaxQuantity()) * 100);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    public Long getMaxExpirationDate() {
        return Long.valueOf(properties.getProperty(name + "_expiration_date"));
    }
}
