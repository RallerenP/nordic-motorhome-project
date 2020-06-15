package com.nordicmotorhome.motorhomerentals.UI.dto;

import com.nordicmotorhome.motorhomerentals.UI.model.MotorhomeModel;

//Author : RAP
// Used for creating candidates when searching for a motorhome - basically 'bare minimum' motorhome models
public class MotorhomeSearchDTO {
    private MotorhomeModel motorhome;
    private double price;

    public MotorhomeSearchDTO(MotorhomeModel motorhome, double price) {
        this.motorhome = motorhome;
        this.price = price;
    }

    public MotorhomeModel getMotorhome() {
        return motorhome;
    }

    public double getPrice() {
        return price;
    }
}
