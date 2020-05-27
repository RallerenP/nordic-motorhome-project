package com.nordicmotorhome.motorhomerentals.domain.entities;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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
    private ArrayList<Double> fees;
    private long averageKilometers;

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

    private void calculateFees() {
        // Average kilometers
        averageKilometers = (endKilometers - startKilometers) / ChronoUnit.DAYS.between(startDate, endDate);

        // Find season and get multiplier
        Month month = startDate.getMonth();
        double multiplier = 1;

        if (month.equals(Month.JANUARY) || month.equals(Month.FEBRUARY) || month.equals(Month.MARCH)) {
            multiplier *= 0.3;
        }

        else if (month.equals(Month.APRIL) ||month.equals(Month.MAY) || month.equals(Month.JUNE) ||
                month.equals(Month.JULY) || month.equals(Month.AUGUST) || month.equals(Month.SEPTEMBER)) {
            multiplier *= 0.6;
        }


        // Calculate fees from multiplier and distance
        if (averageKilometers > 400) {
            averageKilometers = averageKilometers - 400;
            fees.add(averageKilometers * multiplier);
        }

        if (pickup_distance != 0) {
            fees.add(pickup_distance * multiplier);
        }

        if (delivery_distance != 0) {
            fees.add(delivery_distance * multiplier);
        }
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
