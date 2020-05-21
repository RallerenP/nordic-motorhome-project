package com.nordicmotorhome.motorhomerentals.data.entity;

import java.time.LocalDate;

public class Rental {
    private int ID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startKilometers;
    private int endKilometers;
    private boolean fuelNeeded;
    private Customer customer;
    private Motorhome motorhome;
    private int pickup_distance;
    private int delivery_distance;

    public Rental(int id, LocalDate startDate, LocalDate endDate, int startKilometers, int endKilometers, boolean fuelNeeded,
                  Customer customer, Motorhome motorhome, int pickup_distance, int delivery_distance) {
        this.ID = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKilometers = startKilometers;
        this.endKilometers = endKilometers;
        this.fuelNeeded = fuelNeeded;
        this.customer = customer;
        this.motorhome = motorhome;
        this.pickup_distance = pickup_distance;
        this.delivery_distance = delivery_distance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public int getPickup_distance() {
        return pickup_distance;
    }

    public void setPickup_distance(int pickup_distance) {
        this.pickup_distance = pickup_distance;
    }

    public int getDelivery_distance() {
        return delivery_distance;
    }

    public void setDelivery_distance(int delivery_distance) {
        this.delivery_distance = delivery_distance;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "ID=" + ID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startKilometers=" + startKilometers +
                ", endKilometers=" + endKilometers +
                ", fuelNeeded=" + fuelNeeded +
                ", customer=" + customer +
                ", motorhome=" + motorhome +
                '}';
    }
}
