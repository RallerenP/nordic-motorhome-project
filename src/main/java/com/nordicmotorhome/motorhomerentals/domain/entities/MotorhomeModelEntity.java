package com.nordicmotorhome.motorhomerentals.domain.entities;

import java.util.Objects;

// AUTHOR: RAP, NKJ, AML, ME
public class MotorhomeModelEntity extends BaseEntity {
    private String name;
    private int beds;
    private double price;

    public MotorhomeModelEntity(int id, String name, int beds, double price) {
        this.ID = id;
        this.name = name;
        this.beds = beds;
        this.price = price;
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
        MotorhomeModelEntity that = (MotorhomeModelEntity) o;
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
