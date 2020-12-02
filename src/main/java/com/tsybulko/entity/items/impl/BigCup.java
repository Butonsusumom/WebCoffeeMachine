package com.tsybulko.entity.items.impl;

public class BigCup extends Cup {
    public static final int SIZE = 250;
    public static final String DB_NAME = "big_cups";

    public BigCup() {
        super("Big cup", 0);
    }

    public BigCup(int count) {
        super("Big cup", count);
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getCanonicalName() {
        return "big_cups";
    }

    @Override
    public int getMaxCount() {
        return coffeeMachine.getBigCupsMaxCount();
    }

    @Override
    public String getDBName() {
        return DB_NAME;
    }
}
