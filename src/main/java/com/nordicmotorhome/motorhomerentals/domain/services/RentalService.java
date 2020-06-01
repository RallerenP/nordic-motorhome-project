package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.*;
import com.nordicmotorhome.motorhomerentals.domain.orderlines.RentalOrderLines;
import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RentalEntityModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RentalService {
    IDataFacade dataFacade = DataFacadeImpl.getInstance();

    IEntityModelMapper<RentalEntity, RentalModel> remm = new RentalEntityModelMapper();

    // AUTHOR: RAP
    public Message create(
            int customerID,
            LocalDate startDate,
            LocalDate endDate,
            int motorhomeID,
            int pickupDistance,
            int deliveryDistance
    ) {
        try {
            CustomerEntity ce = dataFacade.getCustomerById(customerID);
            MotorhomeEntity me = dataFacade.getMotorhomeById(motorhomeID);

            RentalEntity re = new RentalEntity(
                    0,
                    startDate,
                    endDate,
                    me.getKilometersDriven(),
                    0,
                    false,
                    ce,
                    me,
                    pickupDistance,
                    deliveryDistance,
                    null
            );

            return new Message(MessageType.SUCCESS, remm.mapToModel(dataFacade.createRental(re)));

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;

            // TODO: Return better messages
        }
    }

    //AUTHOR: RAP
//    public RentalOrderLines getBillingInfo(int id) {
//
//    }

    // AUTHOR: RAP
    public Message getBillingInfo(HashMap<AccessoryModel, Integer> accessories, int motorhome_id, LocalDate start, LocalDate end) {
        try {
            ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

            RentalAccessoryEntity rae;

            // Creating a temporary (fake) RentalEntity object responsible for generating the billing.
            RentalEntity tempRe = new RentalEntity(
                    0,
                    start,
                    end,
                    0,
                    0,
                    false,
                    null,
                    dataFacade.getMotorhomeById(motorhome_id),
                    0,
                    0,
                    null
                    );

            for (AccessoryModel accessory : accessories.keySet()) {
                rae = new RentalAccessoryEntity(tempRe, dataFacade.getAccessoryById(accessory.getID()),accessories.get(accessory));
                rentalAccessoryEntities.add(rae);
            }

            tempRe.setAccessoryEntities(rentalAccessoryEntities);

            return new Message(MessageType.SUCCESS, tempRe.generateBillingInfo());
        } catch (NoSuchEntityException e){
            e.printStackTrace(); // TODO return better message
            return new Message(MessageType.ERROR, "Unknown Error");
        }
    };

    // AUTHOR: TODO
    public List<RentalModel> findRentals(){
        try {
            ArrayList<RentalEntity> re = (ArrayList<RentalEntity>) dataFacade.getAllRentals();
            return remm.mapAllToModel(re);
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    // AUTHOR: NKJ
    public void cancelRantal(int id){
        try {
            RentalEntity entity = dataFacade.getRentalById(id);
            dataFacade.deleteRental(entity);
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
    }
}
