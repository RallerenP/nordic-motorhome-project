package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.RentalAccessoryModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<RentalAccessoryModel> mapAllToModel(List<RentalAccessoryEntity> entities) {
        ArrayList<RentalAccessoryModel> ram = new ArrayList<>();
        for (RentalAccessoryEntity entity : entities) {
            ram.add(mapToModel(entity));
        }
        return ram;
    }


}
