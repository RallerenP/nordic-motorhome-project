package com.nordicmotorhome.motorhomerentals.domain.entities;

public class RentalAccessoryEntity extends BaseEntity {
    private RentalEntity rental;
    private AccessoryEntity accessoryEntity;
    private int amount;

    public RentalAccessoryEntity(RentalEntity rental, AccessoryEntity accessoryEntity, int amount) {
        this.rental = rental;
        this.accessoryEntity = accessoryEntity;
        this.amount = amount;
    }

    public RentalEntity getRental() {
        return rental;
    }

    public void setRental(RentalEntity rental) {
        this.rental = rental;
    }

    public AccessoryEntity getAccessoryEntity() {
        return accessoryEntity;
    }

    public void setAccessoryEntity(AccessoryEntity accessoryEntity) {
        this.accessoryEntity = accessoryEntity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
