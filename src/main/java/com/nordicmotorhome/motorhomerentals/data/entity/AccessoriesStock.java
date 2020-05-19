package com.nordicmotorhome.motorhomerentals.data.entity;

public class AccessoriesStock {
    private Accessories accessories;
    private int amount;

    public AccessoriesStock(Accessories accessories, int amount) {
        this.accessories = accessories;
        this.amount = amount;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
