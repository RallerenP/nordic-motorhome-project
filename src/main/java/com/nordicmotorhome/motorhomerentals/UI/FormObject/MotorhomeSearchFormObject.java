package com.nordicmotorhome.motorhomerentals.UI.FormObject;

//Author : RAP, AML
public class MotorhomeSearchFormObject {
    private int beds;
    private String startDate;
    private String endDate;

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
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
}
