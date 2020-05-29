package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;

import java.util.ArrayList;
import java.util.List;

public class AccessoryEntityModelMapper implements IEntityModelMapper<AccessoryEntity, AccessoryModel> {
    @Override
    public AccessoryModel mapToModel(AccessoryEntity entity) {
        return new AccessoryModel(
                entity.getID(),
                entity.getName(),
                entity.getPrice()
        );
    }

    @Override
    public ArrayList<AccessoryModel> mapAllToModel(List<AccessoryEntity> entities) {
        ArrayList<AccessoryModel> am = new ArrayList<>();
        for (AccessoryEntity entity : entities) {
            am.add(mapToModel(entity));
        }
        return am;
    }


}
