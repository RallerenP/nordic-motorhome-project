package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Accessory;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class AccessoryMapper {

    public Accessory insert(Accessory accessory) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO accessory (name, price) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accessory.getName());
            ps.setDouble(2, accessory.getPrice());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();
            rs.next();

            int id = rs.getInt(1);
            accessory.setID(id);

            return accessory;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Accessory get(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM accessory WHERE id = ?";
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

    public Accessory update(Accessory model) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE accessory WHERE id = ?, name = ?, price = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.setString(2, model.getName());
            ps.setDouble(3, model.getPrice());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Accessory model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM accessory WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Accessory load(ResultSet rs) throws SQLException {
        return new Accessory(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
        );
    }
}
