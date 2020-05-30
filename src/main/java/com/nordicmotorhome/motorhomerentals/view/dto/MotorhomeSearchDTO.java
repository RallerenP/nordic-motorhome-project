package com.nordicmotorhome.motorhomerentals.view.dto;

import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModel;

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
