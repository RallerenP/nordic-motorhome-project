package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryStockEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// AUTHOR: RAP, NKJ, AML, ME
public class AccessoryStockRepository implements IRepository<AccessoryStockEntity> {

    @Override
    public AccessoryStockEntity getById(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessories_stock WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);


            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();
            rs.next();
            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccessoryStockEntity> getAll() throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessory_stock";

            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            ArrayList<AccessoryStockEntity> entities = new ArrayList<>();

            while (rs.next()) {
                entities.add(load(rs));
            }

            if (entities.size() == 0) throw new NoSuchEntityException();

            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccessoryStockEntity create(AccessoryStockEntity entity) {
        AccessoryRepository am = new AccessoryRepository();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO accessories_stock (accessories_id, amount) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, entity.getAccessoryEntity().getID());
            ps.setInt(2, entity.getAmount());
            ps.executeUpdate();

            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(AccessoryStockEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM accessories_stock WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, entity.getAccessoryEntity().getID());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessoryStockEntity save(AccessoryStockEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE accessories_stock SET amount = ? WHERE accessories_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, entity.getAmount());
            ps.setInt(2, entity.getAccessoryEntity().getID());

            ps.executeUpdate();

            return this.getById(entity.getAccessoryEntity().getID());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccessoryStockEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessories_stock WHERE " + key + " = ?";
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
    public AccessoryStockEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessories_stock WHERE " + key + " = ?";
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
    public List<AccessoryStockEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessories_stock WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<AccessoryStockEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccessoryStockEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessories_stock WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<AccessoryStockEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccessoryStockEntity load(ResultSet rs) throws SQLException, NoSuchEntityException {
        AccessoryRepository ar = new AccessoryRepository();
        return new AccessoryStockEntity(
                ar.getById(rs.getInt("accessories_id")),
                rs.getInt("amount")
        );
    }
}
