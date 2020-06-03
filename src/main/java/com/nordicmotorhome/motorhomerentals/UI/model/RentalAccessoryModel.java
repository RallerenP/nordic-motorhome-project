package com.nordicmotorhome.motorhomerentals.UI.model;

//Author : RAP
public class RentalAccessoryModel {
    private int rental_id;
    private AccessoryModel accessory;
    private int amount;

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public AccessoryModel getAccessory() {
        return accessory;
    }

    public void setAccessory(AccessoryModel accessory) {
        this.accessory = accessory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RentalAccessoryModel(int rental_id, AccessoryModel accessory, int amount) {
        this.rental_id = rental_id;
        this.accessory = accessory;
        this.amount = amount;
    }
}
