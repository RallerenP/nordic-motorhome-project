package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeModelEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<MotorhomeModelModel> mapAllToModel(List<MotorhomeModelEntity> entities) {
        ArrayList<MotorhomeModelModel> mmm = new ArrayList<>();
        for (MotorhomeModelEntity entity : entities) {
            mmm.add(mapToModel(entity));
        }
        return mmm;
    }


}
