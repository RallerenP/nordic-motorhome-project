package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<MotorhomeModel> mapAllToModel(List<MotorhomeEntity> entities) {
        ArrayList<MotorhomeModel> mm = new ArrayList<>();
        for (MotorhomeEntity entity : entities) {
            mm.add(mapToModel(entity));
        }
        return mm;
    }


}
