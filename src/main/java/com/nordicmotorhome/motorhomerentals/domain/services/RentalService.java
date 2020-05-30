package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.view.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeModelEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.MotorhomeEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.MotorhomeModelEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RentalEntityModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    IDataFacade dataFacade = DataFacadeImpl.getInstance();

    IEntityModelMapper<RentalEntity, RentalModel> remm = new RentalEntityModelMapper();
    IEntityModelMapper<MotorhomeModelEntity, MotorhomeModelModel> mmemm = new MotorhomeModelEntityModelMapper();
    IEntityModelMapper<MotorhomeEntity, MotorhomeModel> memm = new MotorhomeEntityModelMapper();
    public RentalModel create(
            int customerID,
            LocalDate startDate,
            LocalDate endDate,
            int motorhomeID,
            int pickupDistance,
            int deliveryDistance,
            int startKm
    ) {
        try {
            CustomerEntity ce = dataFacade.getCustomerById(customerID);
            MotorhomeEntity me = dataFacade.getMotorhomeById(motorhomeID);

            RentalEntity re = new RentalEntity(
                    0,
                    startDate,
                    endDate,
                    startKm,
                    0,
                    false,
                    ce,
                    me,
                    pickupDistance,
                    deliveryDistance,
                    null
            );

            return remm.mapToModel(dataFacade.createRental(re));

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;

            // TODO: Return better messages
        }
    }

    public List<MotorhomeModelModel> searchMotorhomes(int beds) {
        try {
            return mmemm.mapAllToModel(dataFacade.findAllMotorhomeModels("beds", beds));
        }catch (NoSuchEntityException e) {
            return null;
        }
    }

    // Author: RAP
    public List<MotorhomeModel> searchMotorhomes(int beds, LocalDate startDate, LocalDate endDate) {
        try {
            ArrayList<MotorhomeModelEntity> motorhomeModelEntities = (ArrayList<MotorhomeModelEntity>) dataFacade.findAllMotorhomeModels("beds", beds);
            ArrayList<MotorhomeEntity> candidates = new ArrayList<>();

            for (MotorhomeModelEntity model : motorhomeModelEntities) {
                ArrayList<MotorhomeEntity> motorhomeEntities = (ArrayList<MotorhomeEntity>) dataFacade.findAllMotorhomes("model_id", model.getID());

                for (MotorhomeEntity entity : motorhomeEntities) {
                    try {
                        ArrayList<RentalEntity> rentalEntities = (ArrayList<RentalEntity>) dataFacade.findAllRentals("motorhome_id", entity.getID());

                        for (RentalEntity re : rentalEntities) {
                            if (re.getStartDate().isBefore(startDate) && re.getEndDate().isAfter(startDate)) continue;
                            if (re.getStartDate().isBefore(endDate) && re.getEndDate().isAfter(endDate)) continue;
                            if (re.getStartDate().isBefore(endDate) && re.getEndDate().isAfter(startDate)) continue;
                            if (re.getStartDate().isBefore(startDate) && re.getEndDate().isAfter(endDate)) continue;

                            candidates.add(re.getMotorhomeEntity());
                        }
                    } catch (NoSuchEntityException e) {
                        candidates.add(entity);
                    }
                }
            }

            return memm.mapAllToModel(candidates);
        } catch (NoSuchEntityException e) {
            return null; // TODO: return something better
        }
    };
    public List<RentalModel> findRentals(){
        try {
            ArrayList<RentalEntity> re = (ArrayList<RentalEntity>) dataFacade.getAllRentals();
            return remm.mapAllToModel(re);
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }
}
