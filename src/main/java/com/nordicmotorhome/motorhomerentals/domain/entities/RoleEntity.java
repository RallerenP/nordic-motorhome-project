package com.nordicmotorhome.motorhomerentals.domain.entities;

// AUTHOR: RAP, NKJ, AML, ME
// entity for roles, extends BaseEntity, and is used for conversion to model
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
