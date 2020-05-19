package com.nordicmotorhome.motorhomerentals.data.entity;

public class RentalAccessories {
    private Rentals rentals;
    private Accessories accessories;

    public RentalAccessories(Rentals rentals, Accessories accessories) {
        this.rentals = rentals;
        this.accessories = accessories;
    }

    public Rentals getRentals() {
        return rentals;
    }

    public void setRentals(Rentals rentals) {
        this.rentals = rentals;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }
}
