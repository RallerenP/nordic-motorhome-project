package com.nordicmotorhome.motorhomerentals;

import com.nordicmotorhome.motorhomerentals.MVC.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.data.entity.StaffEntity;
import com.nordicmotorhome.motorhomerentals.data.repositories.StaffRepository;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import com.nordicmotorhome.motorhomerentals.domain.services.RentalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MotorhomeRentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotorhomeRentalsApplication.class, args);

        CustomerService cs = new CustomerService();
        RentalService rs = new RentalService();

        StaffRepository sr = new StaffRepository();
        try {
            StaffModel sm = new StaffEntityModelMapper().mapToModel(sr.getById(1));
            CustomerModel ce = cs.create("Bob", "Bob", 88888888, "bob@bob.dk", "2605204555", sm);

            RentalModel rm = rs.create(ce.getID(), LocalDate.now(), LocalDate.now().plusWeeks(2), 5, 0, 0, 0);

        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }


    }
}
