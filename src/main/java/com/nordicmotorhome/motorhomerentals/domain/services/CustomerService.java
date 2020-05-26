package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.MVC.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.entity.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.data.repositories.CustomerRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.IRepository;
import com.nordicmotorhome.motorhomerentals.domain.mappers.CustomerEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;

public class CustomerService {
    private final IEntityModelMapper<CustomerEntity, CustomerModel> cemm = new CustomerEntityModelMapper();
    private final IRepository<CustomerEntity> customerRepository = new CustomerRepository();

    public CustomerModel create(String firstName, String lastName, int number, String email, String cpr, StaffModel auth) {
        if (auth == null) return null; // TODO: Throw exception

        CustomerEntity ce = new CustomerEntity(0, firstName, lastName, number, email, cpr);
        ce = customerRepository.create(ce);

        return cemm.mapToModel(ce);
    }
}
