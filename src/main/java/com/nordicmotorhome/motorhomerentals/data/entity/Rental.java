package com.nordicmotorhome.motorhomerentals.data.entity;

import java.util.Date;

public class Rental {
    private int ID;
    private Date startDate;
    private Date endDate;
    private int startKilometers;
    private int endKilometers;
    private boolean fuelNeeded;
    private Customer customer;
    private Motorhome motorhome;

    public Rental(int id, Date startDate, Date endDate, int startKilometers, int endKilometers, boolean fuelNeeded,
                  Customer customer, Motorhome motorhome) {
        this.ID = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKilometers = startKilometers;
        this.endKilometers = endKilometers;
        this.fuelNeeded = fuelNeeded;
        this.customer = customer;
        this.motorhome = motorhome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStartKilometers() {
        return startKilometers;
    }

    public void setStartKilometers(int startKilometers) {
        this.startKilometers = startKilometers;
    }

    public int getEndKilometers() {
        return endKilometers;
    }

    public void setEndKilometers(int endKilometers) {
        this.endKilometers = endKilometers;
    }

    public boolean isFuelNeeded() {
        return fuelNeeded;
    }

    public void setFuelNeeded(boolean fuelNeeded) {
        this.fuelNeeded = fuelNeeded;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Motorhome getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(Motorhome motorhome) {
        this.motorhome = motorhome;
    }
}
