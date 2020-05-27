package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;

public class MotorhomeEntityModelMapper implements IEntityModelMapper<MotorhomeEntity, MotorhomeModel> {

    @Override
    public MotorhomeModel mapToModel(MotorhomeEntity entity) {
        MotorhomeModelModel mmm = new MotorhomeModelEntityModelMapper().mapToModel(entity.getMotorhomeModelEntity());


        return new MotorhomeModel(
                entity.getID(),
                mmm,
                entity.getKilometersDriven(),
                entity.isCleaned(),
                entity.isServiced()
        );
    }
}
