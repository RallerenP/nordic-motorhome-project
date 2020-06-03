package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// AUTHOR: RAP, NKJ, AML, ME
public class RentalAccessoriesRepository implements IRepository<RentalAccessoryEntity> {
    @Override
    public RentalAccessoryEntity getById(int id) throws NoSuchEntityException {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories where rental_id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, id);
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            if (!rs.next()) throw new NoSuchEntityException();
            return load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RentalAccessoryEntity> getAll() throws NoSuchEntityException {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();

            ArrayList<RentalAccessoryEntity> rae = new ArrayList<>();

            while(rs.next()) rae.add(load(rs));

            if (rae.size() == 0) throw new NoSuchEntityException();
            return rae;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RentalAccessoryEntity create(RentalAccessoryEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO rental_accessories (rental_id, accessories_id, amount) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, entity.getRental().getID());
            ps.setInt(2, entity.getAccessory().getID());
            ps.setInt(3, entity.getAmount());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()) throw new NoSuchEntityException();

            return getById(rs.getInt(1));
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(RentalAccessoryEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "DELETE FROM rental_accessories WHERE rental_id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, entity.getRental().getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RentalAccessoryEntity save(RentalAccessoryEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE rental_accessories SET rental_id = ?, accessories_id = ?, amount = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, entity.getRental().getID());
            ps.setInt(2, entity.getAccessory().getID());
            ps.setInt(3, entity.getAmount());

            ps.executeUpdate();

            ResultSet rs = ps.getResultSet();

            if (!rs.next()) throw new NoSuchEntityException();

            return load(rs);
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RentalAccessoryEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories WHERE " + key +" = ?";
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
    public RentalAccessoryEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories WHERE " + key + " = ?";
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
    public ArrayList<RentalAccessoryEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<RentalAccessoryEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<RentalAccessoryEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rental_accessories WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<RentalAccessoryEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RentalAccessoryEntity load(ResultSet rs) throws SQLException, NoSuchEntityException {
        final IRepository<RentalEntity> rtr = new RentalRepository();
        final IRepository<AccessoryEntity> ar = new AccessoryRepository();

        return new RentalAccessoryEntity(
                rtr.getById(rs.getInt("rental_id")),
                ar.getById(rs.getInt("accessories_id")),
                rs.getInt("amount")
        );
    }
}
