package com.nordicmotorhome.motorhomerentals.data.entity;

public class AccessoriesStock {
    private Accessory accessory;
    private int amount;

    public AccessoriesStock(Accessory accessory, int amount) {
        this.accessory = accessory;
        this.amount = amount;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
