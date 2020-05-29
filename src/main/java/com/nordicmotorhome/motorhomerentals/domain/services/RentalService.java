package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeModelEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.MotorhomeModelEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RentalEntityModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    IDataFacade dataFacade = DataFacadeImpl.getInstance();

    IEntityModelMapper<RentalEntity, RentalModel> remm = new RentalEntityModelMapper();
    IEntityModelMapper<MotorhomeModelEntity, MotorhomeModelModel> mmemm = new MotorhomeModelEntityModelMapper();
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

    public List<MotorhomeModelModel> findAllMotorhomes(int beds) {
        try {
            return mmemm.mapAllToModel(dataFacade.findAllMotorhomeModels("beds", beds));
        }catch (NoSuchEntityException e) {
            return null;
        }
    }
}
