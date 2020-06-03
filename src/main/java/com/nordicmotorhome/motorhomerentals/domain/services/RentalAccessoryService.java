package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.UI.model.RentalAccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RentalAccessoryEntityModelMapper;

import java.util.ArrayList;

public class RentalAccessoryService {
    IDataFacade dataFacade = new DataFacadeImpl();
    IEntityModelMapper<RentalAccessoryEntity, RentalAccessoryModel> raemm = new RentalAccessoryEntityModelMapper();
    //Author : RAP
    public Message create(int rental_id, int accessory_id, int amount) {
        try {
            RentalAccessoryEntity rea = new RentalAccessoryEntity(dataFacade.getRentalById(rental_id), dataFacade.getAccessoryById(accessory_id), amount);
            dataFacade.createRentalAccessory(rea);
            return new Message(MessageType.SUCCESS, raemm.mapToModel(rea));
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return new Message(MessageType.ERROR, "An unknown error occured");
        }
    }

    // AUTHOR: NKJ
    public Message cancelAccessoryRental(int id){
        try {
            ArrayList<RentalAccessoryEntity> accessoryEntity = (ArrayList<RentalAccessoryEntity>) dataFacade.findAllRentalAccessories("rental_id", id);
            for (RentalAccessoryEntity entity : accessoryEntity) {
                dataFacade.deleteRentalAccessory(entity);
            }

            return new Message(MessageType.SUCCESS, "Success");
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No RentalAccessory found with id '" + id + "'");
        }
    }
}
