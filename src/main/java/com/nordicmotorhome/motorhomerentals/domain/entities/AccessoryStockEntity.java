package com.nordicmotorhome.motorhomerentals.domain.entities;

// AUTHOR: RAP, NKJ, AML, ME
// entity for accessories stock, extends BaseEntity, and is used for conversion to model
public class AccessoryStockEntity extends BaseEntity {
    private AccessoryEntity accessoryEntity;
    private int amount;

    public AccessoryStockEntity(AccessoryEntity accessoryEntity, int amount) {
        this.ID = accessoryEntity.getID();
        this.accessoryEntity = accessoryEntity;
        this.amount = amount;
    }

    public AccessoryEntity getAccessoryEntity() {
        return accessoryEntity;
    }

    public void setAccessoryEntity(AccessoryEntity accessoryEntity) {
        this.accessoryEntity = accessoryEntity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
