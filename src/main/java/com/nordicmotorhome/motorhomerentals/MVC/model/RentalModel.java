package com.nordicmotorhome.motorhomerentals.MVC.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class RentalModel {
    private int ID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startKm;
    private int endKm;
    private boolean fuelNeeded;
    private CustomerModel customer;
    private MotorhomeModel motorhome;
    private ArrayList<RentalAccessoryModel> accessories;
    private int pickupDistance;
    private int deliveryDistance;

    public RentalModel(int ID, LocalDate startDate, LocalDate endDate, int startKm, int endKm, boolean fuelNeeded, CustomerModel customer, MotorhomeModel motorhome, ArrayList<RentalAccessoryModel> accessories, int pickupDistance, int deliveryDistance) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKm = startKm;
        this.endKm = endKm;
        this.fuelNeeded = fuelNeeded;
        this.customer = customer;
        this.motorhome = motorhome;
        this.accessories = accessories;
        this.pickupDistance = pickupDistance;
        this.deliveryDistance = deliveryDistance;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getStartKm() {
        return startKm;
    }

    public int getEndKm() {
        return endKm;
    }

    public boolean isFuelNeeded() {
        return fuelNeeded;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public MotorhomeModel getMotorhome() {
        return motorhome;
    }

    public ArrayList<RentalAccessoryModel> getAccessories() {
        return accessories;
    }

    public int getPickupDistance() {
        return pickupDistance;
    }

    public int getDeliveryDistance() {
        return deliveryDistance;
    }
}
