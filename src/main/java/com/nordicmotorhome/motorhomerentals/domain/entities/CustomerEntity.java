// AUTHOR: NKJ
package com.nordicmotorhome.motorhomerentals.domain.entities;

// AUTHOR: RAP, NKJ, AML, ME
// entity for customers, extends BaseEntity, and is used for conversion to model
public class CustomerEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private int number;
    private String email;
    private String CPR;

    public CustomerEntity(int id, String firstName, String lastName, int number, String email, String cpr) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
        this.CPR = cpr;
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

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                ", CPR='" + CPR + '\'' +
                '}';
    }
}
