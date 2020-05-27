package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.*;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;

import java.util.ArrayList;

public class RentalEntityModelMapper implements IEntityModelMapper<RentalEntity, RentalModel> {

    @Override
    public RentalModel mapToModel(RentalEntity entity) {
        CustomerModel cm = new CustomerEntityModelMapper().mapToModel(entity.getCustomerEntity());
        MotorhomeModel mm = new MotorhomeEntityModelMapper().mapToModel(entity.getMotorhomeEntity());

        ArrayList<RentalAccessoryModel> accessories = new ArrayList<>();

        IEntityModelMapper<RentalAccessoryEntity, RentalAccessoryModel> raemm = new RentalAccessoryEntityModelMapper();

        for (RentalAccessoryEntity accessory : entity.getAccessoryEntities()) {
            accessories.add(raemm.mapToModel(accessory));
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
