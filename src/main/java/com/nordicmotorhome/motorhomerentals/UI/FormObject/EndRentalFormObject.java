package com.nordicmotorhome.motorhomerentals.UI.FormObject;

public class EndRentalFormObject {

    private int kmDriven;
    private boolean fuelNeeded;
    private boolean serviceNeeded;
    private boolean cleaningNeeded;

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public boolean isFuelNeeded() {
        return fuelNeeded;
    }

    public void setFuelNeeded(boolean fuelNeeded) {
        this.fuelNeeded = fuelNeeded;
    }

    public boolean isServiceNeeded() {
        return serviceNeeded;
    }

    public void setServiceNeeded(boolean serviceNeeded) {
        this.serviceNeeded = serviceNeeded;
    }

    public boolean isCleaningNeeded() {
        return cleaningNeeded;
    }

    public void setCleaningNeeded(boolean cleaningNeeded) {
        this.cleaningNeeded = cleaningNeeded;
    }
}
