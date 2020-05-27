package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeModelEntity;

public class MotorhomeModelEntityModelMapper implements IEntityModelMapper<MotorhomeModelEntity, MotorhomeModelModel> {
    @Override
    public MotorhomeModelModel mapToModel(MotorhomeModelEntity entity) {
        return new MotorhomeModelModel(
                entity.getID(),
                entity.getName(),
                entity.getBeds(),
                entity.getPrice()
        );
    }
}
