package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.RoleModel;
import com.nordicmotorhome.motorhomerentals.data.entity.RoleEntity;

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
            default:
                throw new IllegalStateException("Unexpected value: " + entity.getName());
        }
    }

}
