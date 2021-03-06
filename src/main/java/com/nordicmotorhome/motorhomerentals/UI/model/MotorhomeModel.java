package com.nordicmotorhome.motorhomerentals.UI.model;

//Author : RAP
//Used as a model for each motorhome, can only be created, no setters only getters
public class MotorhomeModel {
    private int ID;
    private MotorhomeModelModel model;
    private int kmDriven;
    private boolean cleaned;
    private boolean serviced;

    public MotorhomeModel(int ID, MotorhomeModelModel model, int kmDriven, boolean cleaned, boolean serviced) {
        this.ID = ID;
        this.model = model;
        this.kmDriven = kmDriven;
        this.cleaned = cleaned;
        this.serviced = serviced;
    }

    public int getID() {
        return ID;
    }

    public MotorhomeModelModel getModel() {
        return model;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public boolean isServiced() {
        return serviced;
    }
}
