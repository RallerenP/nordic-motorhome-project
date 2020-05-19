package com.nordicmotorhome.motorhomerentals.data.entity;

public class Role {
    private String name;
    private int ID;

    public Role(String name, int id) {
        this.name = name;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
