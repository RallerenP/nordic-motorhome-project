package com.nordicmotorhome.motorhomerentals.domain.entities;

public abstract class BaseEntity {
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
