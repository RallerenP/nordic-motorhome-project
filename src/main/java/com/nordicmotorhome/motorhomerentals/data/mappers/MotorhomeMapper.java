package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Motorhome;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotorhomeMapper {
    public MotorhomeMapper() {

    }

    public Motorhome update(Motorhome motorhome) throws NoSuchEntityException {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE motorhomes SET model_id = ?, km_driven = ?, cleaned = ?, serviced = ? WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, motorhome.getMotorhomeModel().getID());
            ps.setInt(2, motorhome.getKilometersDriven());
            ps.setBoolean(3, motorhome.isCleaned());
            ps.setBoolean(4, motorhome.isServiced());
            ps.setInt(5, motorhome.getID());

            ps.executeUpdate();

            return get(motorhome.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Motorhome motorhome) throws NoSuchEntityException {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "DELETE FROM motorhomes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, motorhome.getID());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Motorhome> getAll() throws NoSuchEntityException {
        ArrayList<Motorhome> motorhomes = new ArrayList<>();

        try {
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) motorhomes.add(load(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motorhomes;
    }

    public Motorhome get(int id) throws NoSuchEntityException {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();

            return load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Motorhome load(ResultSet rs) throws SQLException, NoSuchEntityException {
        return new Motorhome(
                rs.getInt("ID"),
                new MotorhomeModelMapper().get(rs.getInt("model_id")),
                rs.getInt("km_driven"),
                rs.getBoolean("cleaned"),
                rs.getBoolean("serviced")

        );
    }
}
