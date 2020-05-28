package com.nordicmotorhome.motorhomerentals.MVC.FormObject;

import java.time.LocalDate;

public class AddRentalFormObject {
    private int customerID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int motorhomeID;
    private int pickupDistance;
    private int deliveryDistance;
    private int startKm;

    public int getStartKm() {
        return startKm;
    }

    public void setStartKm(int startKm) {
        this.startKm = startKm;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public int getMotorhomeID() {
        return motorhomeID;
    }

    public void setMotorhomeID(int motorhomeID) {
        this.motorhomeID = motorhomeID;
    }

    public int getPickupDistance() {
        return pickupDistance;
    }

    public void setPickupDistance(int pickupDistance) {
        this.pickupDistance = pickupDistance;
    }

    public int getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(int deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }
}