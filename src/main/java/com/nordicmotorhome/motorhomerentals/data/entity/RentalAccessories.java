package com.nordicmotorhome.motorhomerentals.data.entity;

public class RentalAccessories {
    private Rentals rentals;
    private Accessory accessory;

    public RentalAccessories(Rentals rentals, Accessory accessory) {
        this.rentals = rentals;
        this.accessory = accessory;
    }

    public Rentals getRentals() {
        return rentals;
    }

    public void setRentals(Rentals rentals) {
        this.rentals = rentals;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }
}
