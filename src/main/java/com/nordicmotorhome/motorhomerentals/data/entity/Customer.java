package com.nordicmotorhome.motorhomerentals.data.entity;

public class Customer {
    private int ID;
    private String firstName;
    private String lastName;
    private int number;
    private String email;
    private String CPR;

    public Customer(int id, String firstName, String lastName, int number, String email, String cpr) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
        this.CPR = cpr;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }
}
