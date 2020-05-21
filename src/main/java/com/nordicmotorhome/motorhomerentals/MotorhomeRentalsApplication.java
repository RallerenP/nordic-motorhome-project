package com.nordicmotorhome.motorhomerentals;

import com.nordicmotorhome.motorhomerentals.data.entity.Customer;
import com.nordicmotorhome.motorhomerentals.data.entity.Motorhome;
import com.nordicmotorhome.motorhomerentals.data.entity.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.data.entity.Rental;
import com.nordicmotorhome.motorhomerentals.data.mappers.MotorhomeMapper;
import com.nordicmotorhome.motorhomerentals.data.mappers.RentalMapper;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.services.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MotorhomeRentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotorhomeRentalsApplication.class, args);

        AuthenticationService as = new AuthenticationService();

        System.out.println(as.login("bob@bob.dk", "12346"));
        System.out.println(as.login("bob@bob.dk", "1234"));

    }
}
