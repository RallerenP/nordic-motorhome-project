package com.nordicmotorhome.motorhomerentals.domain.entities;

import com.nordicmotorhome.motorhomerentals.domain.utils.Season;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// AUTHOR: RAP, NKJ, AML, ME
public class AccessoryEntity extends BaseEntity {
    private String name;
    private double price;

    public AccessoryEntity(int id, String name, double price) {
        this.ID = id;
        this.name = name;
        this.price = price;
    }

    public double getPriceByRentalLength(int days) {
        return (price * days);
    }

    public double getPriceByRentalLength(LocalDate startDate, LocalDate endDate) {
        return getPriceByRentalLength((int) ChronoUnit.DAYS.between(startDate, endDate));
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
