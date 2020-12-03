package com.tsybulko.entity;

/**
 * Enum which represents drink sizes.
 */
public enum DrinkSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    int id;

    DrinkSize(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DrinkSize getDrinkSizeById(int id) {
        DrinkSize[] drinkSizes = DrinkSize.values();
        for (DrinkSize drinkSize : drinkSizes) {
            if (drinkSize.getId() == id) {
                return drinkSize;
            }
        }
        return null;
    }
}
