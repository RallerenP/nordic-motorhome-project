package com.nordicmotorhome.motorhomerentals.domain.entities;

import com.nordicmotorhome.motorhomerentals.domain.utils.Season;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// AUTHOR: RAP, NKJ, AML, ME
public class MotorhomeEntity extends BaseEntity {
    private MotorhomeModelEntity motorhomeModelEntity;
    private int kilometersDriven;
    private boolean cleaned;
    private boolean serviced;

    public MotorhomeEntity(int id, MotorhomeModelEntity motorhomeModelEntity, int kilometersDriven, boolean cleaned, boolean serviced) {
        this.ID = id;
        this.motorhomeModelEntity = motorhomeModelEntity;
        this.kilometersDriven = kilometersDriven;
        this.cleaned = cleaned;
        this.serviced = serviced;
    }

    // AUTHOR: RAP
    public double getBasePriceByRentalLength(int days) {
        return (motorhomeModelEntity.getPrice() * days);
    }

    // AUTHOR: RAP
    public double getPriceByRentalLength(int days, Season season) {
        return (motorhomeModelEntity.getPrice() * days) * season.getMult();
    }

    // AUTHOR: RAP
    public double getPriceByRentalLength(LocalDate startDate, LocalDate endDate) {
        return getPriceByRentalLength((int) ChronoUnit.DAYS.between(startDate, endDate), Season.getSeason(startDate));
    };

    public MotorhomeModelEntity getMotorhomeModelEntity() {
        return motorhomeModelEntity;
    }

    public void setMotorhomeModelEntity(MotorhomeModelEntity motorhomeModelEntity) {
        this.motorhomeModelEntity = motorhomeModelEntity;
    }

    public int getKilometersDriven() {
        return kilometersDriven;
    }

    public void setKilometersDriven(int kilometersDriven) {
        this.kilometersDriven = kilometersDriven;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void setCleaned(boolean cleaned) {
        this.cleaned = cleaned;
    }

    public boolean isServiced() {
        return serviced;
    }

    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }
}
