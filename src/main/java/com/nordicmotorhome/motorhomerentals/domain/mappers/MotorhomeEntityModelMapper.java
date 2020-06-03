package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.UI.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.UI.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;

import java.util.ArrayList;
import java.util.List;
// AUTHOR: RAP, AML
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
