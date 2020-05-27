package com.nordicmotorhome.motorhomerentals.domain.entities;

import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.BaseEntity;

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
