package com.tsybulko.entity.items.impl;

import com.tsybulko.entity.items.Item;

public class Stick extends Item {
    public static final String DB_NAME = "sticks";

    public Stick() {
        super("Stick", 0);
    }

    public Stick(int count) {
        super("Stick", count);
    }

    @Override
    public String getCanonicalName() {
        return "sticks";
    }

    @Override
    public int getMaxCount() {
        return coffeeMachine.getSticksMaxCount();
    }

    @Override
    public String getDBName() {
        return DB_NAME;
    }
}
