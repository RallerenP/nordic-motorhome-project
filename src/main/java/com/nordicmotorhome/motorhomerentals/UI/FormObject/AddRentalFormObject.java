package com.nordicmotorhome.motorhomerentals.UI.FormObject;

import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class AddRentalFormObject {
    private int customerID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int motorhomeID;
    private int pickupDistance;
    private int deliveryDistance;
    private int startKm;
    private HashMap<AccessoryModel, Integer> accessoriesMap = new HashMap<>();

    public HashMap<AccessoryModel, Integer> getAccessoriesMap() {
        return accessoriesMap;
    }

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
