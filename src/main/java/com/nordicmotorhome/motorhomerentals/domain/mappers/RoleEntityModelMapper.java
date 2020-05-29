package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.RoleModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleEntityModelMapper implements IEntityModelMapper<RoleEntity, RoleModel> {

    @Override
    public RoleModel mapToModel(RoleEntity entity) {
        switch (entity.getName()) {
            case "SALES_ASSISTANT":
                return RoleModel.SALES_ASSISTANT;
            case "MECHANIC":
                return RoleModel.MECHANIC;
            case "CLEANER":
                return RoleModel.CLEANER;
            case "BOOK_KEEPER":
                return RoleModel.BOOK_KEEPER;
            default:
                throw new IllegalStateException("Unexpected value: " + entity.getName());
        }
    }

    @Override
    public ArrayList<RoleModel> mapAllToModel(List<RoleEntity> entities) {
        ArrayList<RoleModel> rm = new ArrayList<>();
        for (RoleEntity entity : entities) {
            rm.add(mapToModel(entity));
        }
        return rm;
    }


}
