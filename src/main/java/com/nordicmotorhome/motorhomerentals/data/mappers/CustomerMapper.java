package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Customer;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public List<Customer> getAll() throws NoSuchEntityException {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();

            PreparedStatement sql = connection.prepareStatement("select * from customers");
            ResultSet rs = sql.executeQuery();

            int ID;
            String firstName;
            String lastName;
            int number;
            String email;
            String cpr;
            Customer customer = null;

            while (rs.next()){
                ID = rs.getInt("ID");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                number = rs.getInt("tlf");
                email = rs.getString("email");
                cpr = rs.getString("cpr");

                customer = new Customer(ID, firstName, lastName, number, email, cpr);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer get(int id) throws NoSuchEntityException {
        Customer customer = new Customer();
        try {
            Connection connection = DBManager.getConnection();
            String sql = "select * from customers where ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            int ID;
            String firstName;
            String lastName;
            int number;
            String email;
            String cpr;

            if (rs.next()) {
                ID = rs.getInt("ID");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                number = rs.getInt("tlf");
                email = rs.getString("email");
                cpr = rs.getString("cpr");

                customer = new Customer(ID, firstName, lastName, number, email, cpr);
            }
            else {throw new NoSuchEntityException();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer insert(Customer customer) throws NoSuchEntityException {
        CustomerMapper customerMapper = new CustomerMapper();
        int id = 0;

        try {
            Connection connection = DBManager.getConnection();

            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            int number = customer.getNumber();
            String email = customer.getEmail();
            String cpr = customer.getCPR();

            String sql = "INSERT INTO customers (first_name, last_name, tlf, email, cpr) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, number);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, cpr);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerMapper.get(id);
    }

    public Customer update(Customer customer) throws NoSuchEntityException{
        CustomerMapper customerMapper = new CustomerMapper();
        int id = 0;
        try {
            Connection connection = DBManager.getConnection();

            id = customer.getID();
            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            int number = customer.getNumber();
            String email = customer.getEmail();
            String cpr = customer.getCPR();

            String sql = "UPDATE customers SET first_name=?, last_name=?, tlf=?, email=?, cpr=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, number);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, cpr);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return customerMapper.get(id);
    }

    public boolean delete(Customer customer){
        try {
            Connection connection = DBManager.getConnection();
            String sql = "delete from customers where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, customer.getID());
            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Customer load(ResultSet rs) throws SQLException{
        return new Customer(
                rs.getInt("ID"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("tlf"),
                rs.getString("email"),
                rs.getString("cpr"));
    }
}