package com.nordicmotorhome.motorhomerentals.data.entity;

public class Motorhomes {
    private int ID;
    private MotorhomeModel motorhomeModel;
    private int kilometersDriven;
    private boolean cleaned;
    private boolean serviced;

    public Motorhomes(int id, MotorhomeModel motorhomeModel, int kilometersDriven, boolean cleaned, boolean serviced) {
        this.ID = id;
        this.motorhomeModel = motorhomeModel;
        this.kilometersDriven = kilometersDriven;
        this.cleaned = cleaned;
        this.serviced = serviced;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public MotorhomeModel getMotorhomeModel() {
        return motorhomeModel;
    }

    public void setMotorhomeModel(MotorhomeModel motorhomeModel) {
        this.motorhomeModel = motorhomeModel;
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
