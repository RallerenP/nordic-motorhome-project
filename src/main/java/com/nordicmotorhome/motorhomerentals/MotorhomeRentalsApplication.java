package com.nordicmotorhome.motorhomerentals;

import com.nordicmotorhome.motorhomerentals.data.mappers.CustomerMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class MotorhomeRentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MotorhomeRentalsApplication.class, args);

        CustomerMapper customerMapper = new CustomerMapper();
        System.out.println(customerMapper.getAll());
    }

}
