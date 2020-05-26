package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.data.entity.AccessoryEntity;

public class AccessoryEntityModelMapper implements IEntityModelMapper<AccessoryEntity, AccessoryModel> {
    @Override
    public AccessoryModel mapToModel(AccessoryEntity entity) {
        return new AccessoryModel(
                entity.getID(),
                entity.getName(),
                entity.getPrice()
        );
    }
}
