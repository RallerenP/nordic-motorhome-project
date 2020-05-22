package com.nordicmotorhome.motorhomerentals.data.entity;

public class StaffEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private RoleEntity roleEntity;
    private String email;
    private String password;

    public StaffEntity(int id, String firstName, String lastName, RoleEntity roleEntity, String email, String password) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleEntity = roleEntity;
        this.email = email;
        this.password = password;
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

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + roleEntity +
                ", email='" + email + '\'' +
                '}';
    }
}
