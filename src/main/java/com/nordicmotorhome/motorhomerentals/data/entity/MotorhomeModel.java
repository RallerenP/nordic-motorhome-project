package com.nordicmotorhome.motorhomerentals.data.entity;

import java.util.Objects;

public class MotorhomeModel {
    private int ID;
    private String name;
    private int beds;
    private double price;

    public MotorhomeModel(int id, String name, int beds, double price) {
        this.ID = id;
        this.name = name;
        this.beds = beds;
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

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotorhomeModel that = (MotorhomeModel) o;
        return ID == that.ID &&
                beds == that.beds &&
                Double.compare(that.price, price) == 0 &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, beds, price);
    }
}
