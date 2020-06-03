package com.nordicmotorhome.motorhomerentals.UI.model;

import java.util.Objects;

//Author : RAP
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessoryModel that = (AccessoryModel) o;
        return ID == that.ID &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, price);
    }
}
