package com.nordicmotorhome.motorhomerentals.UI.model;

//Author : RAP
//Used as a model for each motorhome model, can only be created, no setters only getters
public class MotorhomeModelModel {
    private int ID;
    private String name;
    private int beds;
    private double price;

    public MotorhomeModelModel(int ID, String name, int beds, double price) {
        this.ID = ID;
        this.name = name;
        this.beds = beds;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }
}
