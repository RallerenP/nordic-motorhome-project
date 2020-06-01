// AUTHOR: NKJ
package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.CustomerEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements IRepository<CustomerEntity> {
    @Override
    public CustomerEntity getById(int id) throws NoSuchEntityException {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "select * from customers where ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerEntity> getAll() throws NoSuchEntityException {
        List<CustomerEntity> customerEntities = new ArrayList<>();
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
            CustomerEntity customerEntity = null;

            while (rs.next()){
                ID = rs.getInt("ID");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                number = rs.getInt("tlf");
                email = rs.getString("email");
                cpr = rs.getString("cpr");

                customerEntity = new CustomerEntity(ID, firstName, lastName, number, email, cpr);
                customerEntities.add(customerEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerEntities;
    }

    @Override
    public CustomerEntity create(CustomerEntity entity) {
        CustomerRepository customerRepository = new CustomerRepository();

        try {
            Connection connection = DBManager.getConnection();

            String sql = "INSERT INTO customers (first_name, last_name, tlf, email, cpr) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getNumber());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getCPR());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);

            return getById(id);
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(CustomerEntity entity) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "delete from customers where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, entity.getID());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public CustomerEntity save(CustomerEntity entity) {
        CustomerRepository customerRepository = new CustomerRepository();

        try {
            Connection connection = DBManager.getConnection();

            String sql = "UPDATE customers SET first_name=?, last_name=?, tlf=?, email=?, cpr=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getNumber());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getCPR());
            preparedStatement.setInt(6, entity.getID());
            preparedStatement.executeUpdate();

            return getById(entity.getID());
        }catch (SQLException | NoSuchEntityException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomerEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM customers WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomerEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM customers WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM customers WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<CustomerEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CustomerEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM customers WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<CustomerEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerEntity load(ResultSet rs) throws SQLException{
        return new CustomerEntity(
                rs.getInt("ID"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("tlf"),
                rs.getString("email"),
                rs.getString("cpr"));
    }
}