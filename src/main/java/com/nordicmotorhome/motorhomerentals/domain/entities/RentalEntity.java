package com.nordicmotorhome.motorhomerentals.domain.entities;

import com.nordicmotorhome.motorhomerentals.domain.utils.Season;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

// AUTHORS: AML, RAP
public class RentalEntity extends BaseEntity {
    private LocalDate startDate;
    private LocalDate endDate;
    private int startKilometers;
    private int endKilometers;
    private boolean fuelNeeded;
    private CustomerEntity customerEntity;
    private MotorhomeEntity motorhomeEntity;
    private int pickup_distance;
    private int delivery_distance;

    private ArrayList<RentalAccessoryEntity> accessoryEntities = new ArrayList<>();

    public RentalEntity(int id, LocalDate startDate, LocalDate endDate, int startKilometers, int endKilometers, boolean fuelNeeded,
                        CustomerEntity customerEntity, MotorhomeEntity motorhomeEntity, int pickup_distance, int delivery_distance,
                        ArrayList<RentalAccessoryEntity> accessoryEntities) {
        this.ID = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startKilometers = startKilometers;
        this.endKilometers = endKilometers;
        this.fuelNeeded = fuelNeeded;
        this.customerEntity = customerEntity;
        this.motorhomeEntity = motorhomeEntity;
        this.pickup_distance = pickup_distance;
        this.delivery_distance = delivery_distance;
        this.accessoryEntities = accessoryEntities;
    }

    // AUTHOR: AML
    public double calculateCancellationFees() {
        double total = 0.0;

        // Total days
        int totalDays = (int)ChronoUnit.DAYS.between(startDate, endDate);

        // Base prices

        total += getMotorhomeEntity().getMotorhomeModelEntity().getPrice() * totalDays;

        // Find season and get multiplier
        total *= Season.getSeason(startDate).getMult();

        for (RentalAccessoryEntity ent : accessoryEntities) {
            total += ent.calculateFees();
        }

        // Calculate percentage depending on time left before rental start

        return daysBetweenMultiplier(total);
    }

    // AUTHOR: AML
    private double daysBetweenMultiplier(double total) {
        if (ChronoUnit.DAYS.between(LocalDate.now(), startDate) > 50) {
            total *= 0.2;
            if (total < 200) {
                total = 200;
            }
        }

        else if (ChronoUnit.DAYS.between(LocalDate.now(), startDate) > 14) {
            total *= 0.5;
        }

        else if (ChronoUnit.DAYS.between(LocalDate.now(), startDate) > 1) {
            total *= 0.8;
        }

        else {
            total *= 0.95;
        }
        return total;
    }

    // AUTHOR: RAP
    public double calculateFees() {
        double total = 0.0;

        // Total days.
        int totalDays = (int)ChronoUnit.DAYS.between(startDate, endDate);

        // Base prices
        total += getMotorhomeEntity().getPriceByRentalLength(totalDays, Season.getSeason(startDate));

        // Find season and get multiplier

        for (RentalAccessoryEntity ent : accessoryEntities) {
            total += ent.calculateFees();
        }


        // Average kilometers
        long averageKilometers = (endKilometers - startKilometers) / totalDays;


        // Calculate fees from multiplier and distance
        if (averageKilometers > 400) {
            total += averageKilometers - 400;
        }

        total += pickup_distance * 0.70;
        total += delivery_distance * 0.70;

        if (fuelNeeded) total += 70;

        return total;
    }

    public ArrayList<RentalAccessoryEntity> getAccessoryEntities() {
        return accessoryEntities;
    }

    public void setAccessoryEntities(ArrayList<RentalAccessoryEntity> accessoryEntities) {
        this.accessoryEntities = accessoryEntities;
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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public MotorhomeEntity getMotorhomeEntity() {
        return motorhomeEntity;
    }

    public void setMotorhomeEntity(MotorhomeEntity motorhomeEntity) {
        this.motorhomeEntity = motorhomeEntity;
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
                ", customer=" + customerEntity +
                ", motorhome=" + motorhomeEntity +
                '}';
    }
}
