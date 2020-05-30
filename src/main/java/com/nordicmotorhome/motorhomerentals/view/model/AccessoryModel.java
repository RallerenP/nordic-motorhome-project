package com.nordicmotorhome.motorhomerentals.view.model;

public class AccessoryModel {
    private int ID;
    private String name;
    private double price;

    public AccessoryModel(int ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
