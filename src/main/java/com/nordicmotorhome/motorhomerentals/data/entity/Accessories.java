package com.nordicmotorhome.motorhomerentals.data.entity;

public class Accessories {
    private int ID;
    private String name;
    private double price;

    public Accessories(int id, String name, double price) {
        this.ID = id;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
