package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.MVC.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.data.entity.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.data.entity.MotorhomeEntity;
import com.nordicmotorhome.motorhomerentals.data.entity.RentalEntity;
import com.nordicmotorhome.motorhomerentals.data.repositories.CustomerRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.IRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.MotorhomeRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.RentalRepository;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RentalEntityModelMapper;

import java.time.LocalDate;

public class RentalService {
    IRepository<CustomerEntity> customerRepository = new CustomerRepository();
    IRepository<MotorhomeEntity> motorhomeRepository = new MotorhomeRepository();
    IRepository<RentalEntity> rentalRepository = new RentalRepository();
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
            CustomerEntity ce = customerRepository.getById(customerID);
            MotorhomeEntity me = motorhomeRepository.getById(motorhomeID);

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

            return remm.mapToModel(rentalRepository.create(re));

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
            return null;

            // TODO: Return better messages
        }
    }
}
