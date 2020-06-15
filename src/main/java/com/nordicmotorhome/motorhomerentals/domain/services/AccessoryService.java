package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.AccessoryEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;

import java.util.ArrayList;
import java.util.List;

//Author : RAP, AML, NKJ, ME
// Is used for servicing and performing 'commands' given from DomainFacadeImpl, always returning a Message
public class AccessoryService {
    IDataFacade dataFacade = new DataFacadeImpl();

    IEntityModelMapper<AccessoryEntity, AccessoryModel> aemm = new AccessoryEntityModelMapper();

    public Message getAccessory(int id) {
        try {
            AccessoryModel am = aemm.mapToModel(dataFacade.getAccessoryById(id));
            return new Message(MessageType.SUCCESS, am);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "No accessory with id '" + id + "' was found");
        }
    }

    public Message getAllAccessories() {
        try {
            ArrayList<AccessoryModel> models = (ArrayList<AccessoryModel>) aemm.mapAllToModel(dataFacade.getAllAccessories());

            return new Message(MessageType.SUCCESS, models);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.WARNING, "No accessories found");
        }
    }

    public Message getAllAccessories(List<Integer> ids) {
        try {
            ArrayList<AccessoryEntity> accessoryEntities = new ArrayList<>();

            for (int id : ids) {
                accessoryEntities.add(dataFacade.getAccessoryById(id));
            }

            ArrayList<AccessoryModel> models = (ArrayList<AccessoryModel>) aemm.mapAllToModel(accessoryEntities);

            return new Message(MessageType.SUCCESS, models);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.WARNING, "No accessories found"); // TODO better message
        }
    }
}
