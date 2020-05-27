package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.RoleModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.StaffEntity;

public class StaffEntityModelMapper implements IEntityModelMapper<StaffEntity, StaffModel> {

    @Override
    public StaffModel mapToModel(StaffEntity entity) {
        RoleModel role = new RoleEntityModelMapper().mapToModel(entity.getRoleEntity());



        return new StaffModel(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                role
        );
    }
}
