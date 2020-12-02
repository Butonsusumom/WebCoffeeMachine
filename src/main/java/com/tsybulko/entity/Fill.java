package com.tsybulko.entity;

import java.sql.Date;

public class Fill {
    private String name;
    private int count;
    private String user;
    private Date date;

    public Fill(String name, int count, String user, Date date) {
        this.name = name;
        this.count = count;
        this.user = user;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return count;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }
}

