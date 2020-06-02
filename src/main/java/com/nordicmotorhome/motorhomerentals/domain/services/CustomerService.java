package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.CustomerEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;

public class CustomerService {
    private final IEntityModelMapper<CustomerEntity, CustomerModel> cemm = new CustomerEntityModelMapper();
    private final IDataFacade dataFacade = DataFacadeImpl.getInstance();

    public Message create(String firstName, String lastName, int number, String email, String cpr, StaffModel auth) {
        if (auth == null) return null; // TODO: Throw exception

        CustomerEntity ce = new CustomerEntity(0, firstName, lastName, number, email, cpr);
        ce = dataFacade.createCustomer(ce);

        CustomerModel cm = cemm.mapToModel(ce);

        return new Message(MessageType.SUCCESS, cm);
    }

    public Message findCustomer(String cpr){
        try{
            CustomerModel cm = cemm.mapToModel(dataFacade.findOneCustomer("cpr", cpr));
            return new Message(MessageType.SUCCESS, cm);
        }catch (NoSuchEntityException e){
            return new Message(MessageType.ERROR, "No customer found with cpr'" + cpr + "'");
        }
    }

    public Message update(int ID, String firstName, String lastName, String email, int phone) {
        try {
            CustomerEntity ce = dataFacade.getCustomerById(ID);
            ce.setFirstName(firstName);
            ce.setLastName(lastName);
            ce.setEmail(email);
            ce.setNumber(phone);

            ce = dataFacade.saveCustomer(ce);

            CustomerModel cm = cemm.mapToModel(ce);

            return new Message(MessageType.SUCCESS, cm);

        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No customer was found with id '" + ID + "'");
        }
    }
}
