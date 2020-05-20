package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Accessory;
import com.nordicmotorhome.motorhomerentals.data.entity.AccessoryStock;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class AccessoryStockMapper {

    public AccessoryStock insert(AccessoryStock accessoryStock) {
        AccessoryMapper am = new AccessoryMapper();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO accessories_stock (accessories_id, amount) VALUES (?,?)";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, accessoryStock.getAccessory().getID());
            ps.setInt(2, accessoryStock.getAmount());
            ps.executeUpdate();

            return accessoryStock;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccessoryStock get(int id) throws NoSuchEntityException{
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

    public AccessoryStock update(AccessoryStock model) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE accessories_stock WHERE accessories_id = ?, amount = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getAccessory().getID());
            ps.setInt(2, model.getAmount());

            ps.executeUpdate();

            return this.get(model.getAccessory().getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(AccessoryStock model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM accessories_stock WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getAccessory().getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AccessoryStock load(ResultSet rs) throws SQLException, NoSuchEntityException {
        AccessoryMapper am = new AccessoryMapper();
        return new AccessoryStock(
                am.get(rs.getInt("accessories_id")),
                rs.getInt("amount")
        );
    }
}
