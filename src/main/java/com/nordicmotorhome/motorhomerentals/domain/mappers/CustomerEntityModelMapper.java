package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

// AUTHOR: RAP, AML
public class CustomerEntityModelMapper implements IEntityModelMapper<CustomerEntity, CustomerModel> {
    @Override
    public CustomerModel mapToModel(CustomerEntity entity) {
        return new CustomerModel(
                entity.getID(),
                entity.getFirstName(),
                entity.getLastName(),
                String.valueOf(entity.getNumber()),
                entity.getEmail()
        );
    }

    @Override
    public ArrayList<CustomerModel> mapAllToModel(List<CustomerEntity> entities) {
        ArrayList<CustomerModel> cm = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            cm.add(mapToModel(entity));
        }
        return cm;
    }


}
