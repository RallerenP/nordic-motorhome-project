package com.nordicmotorhome.motorhomerentals.domain.entities;

public class RentalAccessoryEntity extends BaseEntity {
    private RentalEntity rental;
    private AccessoryEntity accessory;
    private int amount;

    public RentalAccessoryEntity(RentalEntity rental, AccessoryEntity accessory, int amount) {
        this.rental = rental;
        this.accessory = accessory;
        this.amount = amount;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }

    public AccessoryEntity getAccessory() {
        return accessory;
    }

    public void setAccessory(AccessoryEntity accessory) {
        this.accessory = accessory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
