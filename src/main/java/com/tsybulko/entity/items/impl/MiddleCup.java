package com.tsybulko.entity.items.impl;

public class MiddleCup extends Cup {
    public static final int SIZE = 175;
    public static final String DB_NAME = "middle_cups";

    public MiddleCup() {
        super("Middle cup", 0);
    }

    public MiddleCup(int count) {
        super("Middle cup", count);
    }

    public int getSize() {
        return SIZE;
    }

    @Override
    public String getCanonicalName() {
        return "middle_cups";
    }

    @Override
    public int getMaxCount() {
        return coffeeMachine.getMiddleCupsMaxCount();
    }

    @Override
    public String getDBName() {
        return DB_NAME;
    }
}
