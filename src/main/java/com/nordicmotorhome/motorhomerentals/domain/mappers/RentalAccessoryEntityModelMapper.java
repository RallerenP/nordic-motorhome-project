package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.RentalAccessoryModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;

public class RentalAccessoryEntityModelMapper implements IEntityModelMapper<RentalAccessoryEntity, RentalAccessoryModel> {

    @Override
    public RentalAccessoryModel mapToModel(RentalAccessoryEntity entity) {
        IEntityModelMapper<AccessoryEntity, AccessoryModel> aemm = new AccessoryEntityModelMapper();

        return new RentalAccessoryModel(
                entity.getRental().getID(),
                aemm.mapToModel(entity.getAccessory()),
                entity.getAmount()
        );
    }
}
