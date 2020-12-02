package com.tsybulko.entity.items;

import com.tsybulko.entity.CoffeeMachine;

public abstract class Item {
    private String name;
    private int count;
    private int id;
    protected static CoffeeMachine coffeeMachine = CoffeeMachine.getCoffeeMachineInstance();

    public Item(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public abstract String getCanonicalName();

    public abstract int getMaxCount();

    public void setId(int id) {
        this.id = id;
    }

    public int filledInPercentage() {
        return (int) (((double) count / getMaxCount()) * 100);
    }

    public abstract String getDBName();

    public int getId() {
        return id;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
