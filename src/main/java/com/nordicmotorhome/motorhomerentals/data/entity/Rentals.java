package com.nordicmotorhome.motorhomerentals.data.entity;

import java.util.Date;

public class Rentals {
    private int ID;
    private Date startDate;
    private Date endDate;
    private int startKilometers;
    private int endKilometers;
    private boolean fuelNeeded;
    private Customers customers;
    private Motorhomes motorhomes;

    public Rentals(int id, Date startDate, Date endDate, int startKilometers, int endKilometers, boolean fuelNeeded,
                        Customers customers, Motorhomes motorhomes) {
        this.ID = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKilometers = startKilometers;
        this.endKilometers = endKilometers;
        this.fuelNeeded = fuelNeeded;
        this.customers = customers;
        this.motorhomes = motorhomes;
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

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Motorhomes getMotorhomes() {
        return motorhomes;
    }

    public void setMotorhomes(Motorhomes motorhomes) {
        this.motorhomes = motorhomes;
    }
}
