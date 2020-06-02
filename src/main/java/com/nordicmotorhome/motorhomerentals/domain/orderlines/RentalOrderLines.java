package com.nordicmotorhome.motorhomerentals.domain.orderlines;

import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RentalOrderLines {
    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public String getMotorhomeName() {
        return motorhomeName;
    }

    public void setMotorhomeName(String motorhomeName) {
        this.motorhomeName = motorhomeName;
    }

    public String getMotorhomePrice() {
        return motorhomePrice;
    }

    public void setMotorhomePrice(String motorhomePrice) {
        this.motorhomePrice = motorhomePrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSeasonPrice() {
        return seasonPrice;
    }

    public void setSeasonPrice(String seasonPrice) {
        this.seasonPrice = seasonPrice;
    }

    public Map<String, String> getAccessories() {
        return accessories;
    }

    public void setAccessories(Map<String, String> accessories) {
        this.accessories = accessories;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    private String motorhomeName;
    private String motorhomePrice;
    private String startDate;
    private String endDate;
    private String seasonPrice;

    private Map<String, String> accessories = new HashMap<>();
    private Map<String, String> extras = new HashMap<>();

    private String totalPrice;
}
