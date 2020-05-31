package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.AccessoryEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;

import java.util.ArrayList;
import java.util.List;

public class AccessoryService {
    IDataFacade dataFacade = new DataFacadeImpl();

    IEntityModelMapper<AccessoryEntity, AccessoryModel> aemm = new AccessoryEntityModelMapper();

    public AccessoryModel getAccessory(int id) {
        try {
            return aemm.mapToModel(dataFacade.getAccessoryById(id));
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<AccessoryModel> getAllAccessories() {
        try {
            return (ArrayList<AccessoryModel>) aemm.mapAllToModel(dataFacade.getAllAccessories());
        } catch (NoSuchEntityException e) {
            // TODO: Return something better
            return null;
        }
    }

    public ArrayList<AccessoryModel> getAllAccessories(List<Integer> ids) {
        try {
            ArrayList<AccessoryEntity> accessoryEntities = new ArrayList<>();

            for (int id : ids) {
                accessoryEntities.add(dataFacade.getAccessoryById(id));
            }

            return (ArrayList<AccessoryModel>) aemm.mapAllToModel(accessoryEntities);
        } catch (NoSuchEntityException e) {
            return null; // TODO better message
        }
    }
}
