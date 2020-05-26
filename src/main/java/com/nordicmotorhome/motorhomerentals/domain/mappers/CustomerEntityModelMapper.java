package com.nordicmotorhome.motorhomerentals.domain.mappers;

import com.nordicmotorhome.motorhomerentals.MVC.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.data.entity.CustomerEntity;

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
}
