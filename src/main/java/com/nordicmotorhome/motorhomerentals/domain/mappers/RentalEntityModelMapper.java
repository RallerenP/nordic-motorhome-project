package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.entity.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.data.entity.RentalEntity;

import java.util.ArrayList;

public class RentalEntityModelMapper implements IEntityModelMapper<RentalEntity, RentalModel> {

    @Override
    public RentalModel mapToModel(RentalEntity entity) {
        CustomerModel cm = new CustomerEntityModelMapper().mapToModel(entity.getCustomerEntity());
        MotorhomeModel mm = new MotorhomeEntityModelMapper().mapToModel(entity.getMotorhomeEntity());

        ArrayList<AccessoryModel> accessories = new ArrayList<>();
        AccessoryEntityModelMapper aemm = new AccessoryEntityModelMapper();

        for (AccessoryEntity accessory : entity.getAccessoryEntities()) {
            accessories.add(aemm.mapToModel(accessory));
        }

        return new RentalModel(
                entity.getID(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStartKilometers(),
                entity.getEndKilometers(),
                entity.isFuelNeeded(),
                cm,
                mm,
                accessories,
                entity.getPickup_distance(),
                entity.getDelivery_distance()
        );
    }
}
