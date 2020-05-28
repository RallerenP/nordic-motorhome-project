package com.nordicmotorhome.motorhomerentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@SpringBootApplication
public class MotorhomeRentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotorhomeRentalsApplication.class, args);

       //DBSetup setup = new DBSetup();
       //setup.setup();

    }
}
