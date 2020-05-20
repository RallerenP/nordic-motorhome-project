package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public List<Customer> getAll(){
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from customers";
            ResultSet rs = statement.executeQuery(sql);

            int ID;
            String firstName;
            String lastName;
            int tlf;
            String email;
            String cpr;
            Customer customer = null;

            while (rs.next()){
                ID = rs.getInt("ID");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                tlf = rs.getInt("tlf");
                email = rs.getString("email");
                cpr = rs.getString("cpr");

                customer = new Customer(ID, firstName, lastName, tlf, email, cpr);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getAll Customers! fail!");
        }
        System.out.println(customers);
        return customers;
    }
}
