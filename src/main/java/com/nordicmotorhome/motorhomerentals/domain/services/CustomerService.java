package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.CustomerEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;

public class CustomerService {
    private final IEntityModelMapper<CustomerEntity, CustomerModel> cemm = new CustomerEntityModelMapper();
    private final IDataFacade dataFacade = DataFacadeImpl.getInstance();

    public CustomerModel create(String firstName, String lastName, int number, String email, String cpr, StaffModel auth) {
        if (auth == null) return null; // TODO: Throw exception

        CustomerEntity ce = new CustomerEntity(0, firstName, lastName, number, email, cpr);
        ce = dataFacade.createCustomer(ce);

        return cemm.mapToModel(ce);
    }

    public CustomerModel findCustomer(String cpr){
        try{
            return cemm.mapToModel(dataFacade.findOneCustomer("cpr", cpr));
        }catch (NoSuchEntityException e){
            return null;
        }
    }

    public CustomerModel update(int ID, String firstName, String lastName, String email, int phone) {
        try {
            CustomerEntity ce = dataFacade.getCustomerById(ID);
            ce.setFirstName(firstName);
            ce.setLastName(lastName);
            ce.setEmail(email);
            ce.setNumber(phone);

            ce = dataFacade.saveCustomer(ce);

            return cemm.mapToModel(ce);

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
