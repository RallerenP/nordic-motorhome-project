package com.nordicmotorhome.motorhomerentals.domain.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalAccessoryEntity extends BaseEntity {
    private RentalEntity rental;
    private AccessoryEntity accessory;
    private int amount;

    public RentalAccessoryEntity(RentalEntity rental, AccessoryEntity accessory, int amount) {
        this.rental = rental;
        this.accessory = accessory;
        this.amount = amount;
    }

    double calculateFees() {
        LocalDate startDate = rental.getStartDate();
        LocalDate endDate = rental.getEndDate();

        int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate);

        return (totalDays * amount) * accessory.getPrice();
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
