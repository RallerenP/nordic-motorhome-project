package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.*;
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

// Is used for servicing and performing 'commands' given from DomainFacadeImpl, always returning a Message

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
                    deliveryDistance
            );


            return new Message(MessageType.SUCCESS, remm.mapToModel(dataFacade.createRental(re)));

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;

            // TODO: Return better messages
        }
    }

    //AUTHOR: RAP
    public Message getRental(int id) {
        try {
            return new Message(MessageType.SUCCESS, remm.mapToModel(dataFacade.getRentalById(id)));
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return new Message(MessageType.ERROR, "No rental with id '" + id + "' was found");
        }
    }

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
                    0
                    );

            for (AccessoryModel accessory : accessories.keySet()) {
                rae = new RentalAccessoryEntity(tempRe, dataFacade.getAccessoryById(accessory.getID()),accessories.get(accessory));
                rentalAccessoryEntities.add(rae);


            }

            tempRe.setAccessoryEntities(rentalAccessoryEntities);

            return new Message(MessageType.SUCCESS, tempRe.generateBillingInfo());
        } catch (NoSuchEntityException e){
            e.printStackTrace();
            return new Message(MessageType.ERROR, "Unknown Error");
        }
    };

    // AUTHOR: RAP
    public Message findRentals(){
        try {
            ArrayList<RentalEntity> re = (ArrayList<RentalEntity>) dataFacade.getAllRentals();
            List<RentalModel> list = remm.mapAllToModel(re);
            return new Message(MessageType.SUCCESS, list);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.WARNING, "No rentals found");
        }
    }

    // AUTHOR: NKJ
    public Message cancelRental(int id){
        try {
            RentalEntity entity = dataFacade.getRentalById(id);
            dataFacade.deleteRental(entity);
            return new Message(MessageType.SUCCESS, "Success");
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No rental with id '" + id + "' was found");
        }
    }

    // AUTHOR: RAP
    public Message setRentalFuelNeeded(int id, boolean fuelNeeded) {
        try {
            RentalEntity entity = dataFacade.getRentalById(id);
            entity.setFuelNeeded(fuelNeeded);
            dataFacade.saveRental(entity);
            return new Message(MessageType.SUCCESS, "Success");
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No entity with id '" + id + "' was found in the system");
        }
    }

    // AUTHOR : RAP
    public Message setRentalEndKilometers(int id, int km) {
        try {
            RentalEntity entity = dataFacade.getRentalById(id);
            entity.setEndKilometers(km);
            dataFacade.saveRental(entity);
            return new Message(MessageType.SUCCESS, "Success");
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No entity with id '" + id + "' was found in the system");
        }
    }

    // AUTHOR: AML
    public Message calculateFees(int id) {
        double fee = 0;
        try {
            RentalEntity entity = dataFacade.getRentalById(id);
            fee = entity.calculateFees();
            return new Message(MessageType.SUCCESS, fee);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No rental with id '" + id + "' was found");
        }

    }
}
