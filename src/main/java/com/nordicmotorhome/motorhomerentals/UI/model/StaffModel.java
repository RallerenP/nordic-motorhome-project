package com.nordicmotorhome.motorhomerentals.UI.model;

//Author : RAP
//Used as a model for each staff, can only be created, no setters only getters
public class StaffModel {
    private String firstName;
    private String lastName;
    private String email;
    private RoleModel role;

    public StaffModel(String firstName, String lastName, String email, RoleModel role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public RoleModel getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
