package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalAccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.RentalEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements IRepository<RentalEntity> {
    @Override
    public RentalEntity getById(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);


            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();


            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RentalEntity> getAll() throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals";

            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            ArrayList<RentalEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();

            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RentalEntity create(RentalEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO rentals (start_date, end_date, start_km, end_km, fuel_needed, customer_id, motorhome_id, pickup_distance, delivery_distance) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(entity.getStartDate()));
            ps.setDate(2, Date.valueOf(entity.getEndDate()));
            ps.setInt(3, entity.getStartKilometers());
            ps.setInt(4, entity.getEndKilometers());
            ps.setBoolean(5, entity.isFuelNeeded());
            ps.setInt(6, entity.getCustomerEntity().getID());
            ps.setInt(7, entity.getMotorhomeEntity().getID());
            ps.setInt(8, entity.getPickup_distance());
            ps.setInt(9, entity.getDelivery_distance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();
            int id = rs.getInt(1);
            return getById(id);
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(RentalEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM rentals WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, entity.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RentalEntity save(RentalEntity entity) {
                try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE rentals SET start_date = ?, end_date = ?, start_km = ?, end_km = ?, fuel_needed = ?," +
                            "customer_id = ?, motorhome_id = ?, pickup_distance = ?, delivery_distance = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setDate(1, Date.valueOf(entity.getStartDate()));
            ps.setDate(2, Date.valueOf(entity.getEndDate()));
            ps.setInt(3, entity.getStartKilometers());
            ps.setInt(4, entity.getEndKilometers());
            ps.setBoolean(5, entity.isFuelNeeded());
            ps.setInt(6, entity.getCustomerEntity().getID());
            ps.setInt(7, entity.getMotorhomeEntity().getID());
            ps.setInt(8, entity.getPickup_distance());
            ps.setInt(9, entity.getDelivery_distance());
            ps.setInt(10, entity.getID());

            ps.executeUpdate();

            return getById(entity.getID());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RentalEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, key);
            ps.setString(2, value);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RentalEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, key);
            ps.setInt(2, value);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RentalEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<RentalEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RentalEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<RentalEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RentalEntity load(ResultSet rs) throws SQLException, NoSuchEntityException {
        CustomerRepository cr = new CustomerRepository();
        MotorhomeRepository mr = new MotorhomeRepository();
        RentalAccessoriesRepository rar = new RentalAccessoriesRepository();

        return new RentalEntity(
            rs.getInt("id"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            rs.getInt("start_km"),
            rs.getInt("end_km"),
            rs.getBoolean("fuel_needed"),
            cr.getById(rs.getInt("customer_id")),
            mr.getById(rs.getInt("motorhome_id")),
            rs.getInt("pickup_distance"),
            rs.getInt("delivery_distance"),
            rar.findAll("rental_id", rs.getInt("id"))
        );
    }

}
