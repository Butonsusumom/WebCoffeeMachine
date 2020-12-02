package com.tsybulko.entity.items.impl;

public class LittleCup extends Cup {
    public static final int SIZE = 110;
    public static final String DB_NAME = "little_cups";

    public LittleCup() {
        super("Little cup", 0);
    }

    public LittleCup(int count) {
        super("Little cup", count);
    }

    public int getSize() {
        return SIZE;
    }

    @Override
    public String getCanonicalName() {
        return "little_cups";
    }

    @Override
    public int getMaxCount() {
        return coffeeMachine.getLittleCupsMaxCount();
    }

    @Override
    public String getDBName() {
        return DB_NAME;
    }
}
