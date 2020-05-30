package com.nordicmotorhome.motorhomerentals.view.model;

public class CustomerModel {
    private int ID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public CustomerModel(int ID, String firstName, String lastName, String phone, String email) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getID() {
        return ID;
    }

    public String getPhone() {
        return phone;
    }
}
