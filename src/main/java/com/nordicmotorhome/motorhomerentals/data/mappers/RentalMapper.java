package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Rental;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class RentalMapper {

    public Rental insert(Rental rental) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO rentals (start_date, end_date, start_km, end_km, fuel_needed, customer_id, motorhome_id, pickup_distance, delivery_distance) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(rental.getStartDate()));
            ps.setDate(2, Date.valueOf(rental.getEndDate()));
            ps.setInt(3, rental.getStartKilometers());
            ps.setInt(4, rental.getEndKilometers());
            ps.setBoolean(5, rental.isFuelNeeded());
            ps.setInt(6, rental.getCustomer().getID());
            ps.setInt(7, rental.getMotorhome().getID());
            ps.setInt(8, rental.getPickup_distance());
            ps.setInt(9, rental.getDelivery_distance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();
            rs.next();

            int id = rs.getInt(1);
            rental.setID(id);
            return rental;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rental get(int id) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM rentals WHERE id = ?";
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

    public Rental update(Rental model) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE rentals SET start_date = ?, end_date = ?, start_km = ?, end_km = ?, fuel_needed = ?," +
                            "customer_id = ?, motorhome_id = ?, pickup_distance = ?, delivery_distance = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setDate(1, Date.valueOf(model.getStartDate()));
            ps.setDate(2, Date.valueOf(model.getEndDate()));
            ps.setInt(3, model.getStartKilometers());
            ps.setInt(4, model.getEndKilometers());
            ps.setBoolean(5, model.isFuelNeeded());
            ps.setInt(6, model.getCustomer().getID());
            ps.setInt(7, model.getMotorhome().getID());
            ps.setInt(8, model.getPickup_distance());
            ps.setInt(9, model.getDelivery_distance());
            ps.setInt(10, model.getID());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Rental model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM rentals WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Rental load(ResultSet rs) throws SQLException, NoSuchEntityException {
        CustomerMapper cm = new CustomerMapper();
        MotorhomeMapper mm = new MotorhomeMapper();
        return new Rental(
            rs.getInt("id"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            rs.getInt("start_km"),
            rs.getInt("end_km"),
            rs.getBoolean("fuel_needed"),
            cm.get(rs.getInt("customer_id")),
            mm.get(rs.getInt("motorhome_id")),
            rs.getInt("pickup_distance"),
            rs.getInt("deliver_distance")
        );
    }
}
