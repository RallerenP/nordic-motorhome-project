package com.nordicmotorhome.motorhomerentals.domain.entities;

// AUTHOR: RAP
// A base of each entity, only contains ID because most entities 'should' contain an ID
public abstract class BaseEntity {
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
