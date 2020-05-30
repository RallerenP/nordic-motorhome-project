package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.domain.entities.*;
import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.view.model.MotorhomeModelModel;
import com.nordicmotorhome.motorhomerentals.view.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
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

    public double getIntermediatePrice(List<Integer> accessories, int motorhome_id, LocalDate start, LocalDate end) {
        double total = 0;

        try {
            total += dataFacade.getMotorhomeById(motorhome_id).getPriceByRentalLength(start, end);

            for (int accessory_id : accessories) {
                total += dataFacade.getAccessoryById(accessory_id).getPriceByRentalLength(start, end);
            }

            return total;
        } catch (NoSuchEntityException e){
            e.printStackTrace(); // TODO return better message
            return 0;
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
