package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.MotorhomeModel;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class MotorhomeModelMapper {

    public MotorhomeModel insert(MotorhomeModel motorhomeModel) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO motorhome_models (name, beds, price) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, motorhomeModel.getName());
            ps.setInt(2, motorhomeModel.getBeds());
            ps.setDouble(1, motorhomeModel.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int id = rs.getInt(1);
            motorhomeModel.setID(id);

            return motorhomeModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MotorhomeModel get(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhome_models WHERE id = ?";
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

    public MotorhomeModel update(MotorhomeModel model) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE motorhome_models SET name = ?, beds = ?, price = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, model.getName());
            ps.setInt(2, model.getBeds());
            ps.setDouble(3, model.getPrice());
            ps.setInt(4, model.getID());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(MotorhomeModel model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM motorhome_models WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MotorhomeModel load(ResultSet rs) throws SQLException {
        return new MotorhomeModel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("beds"),
                rs.getDouble("price")
                );
    }

}
