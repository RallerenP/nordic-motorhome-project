package com.nordicmotorhome.motorhomerentals.domain.entities;

public class AccessoryEntity extends BaseEntity {
    private String name;
    private double price;

    public AccessoryEntity(int id, String name, double price) {
        this.ID = id;
        this.name = name;
        this.price = price;
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
