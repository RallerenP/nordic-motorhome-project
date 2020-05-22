package com.nordicmotorhome.motorhomerentals.data.entity;

public class RoleEntity extends BaseEntity {
    private String name;

    public RoleEntity(String name, int id) {
        this.name = name;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
