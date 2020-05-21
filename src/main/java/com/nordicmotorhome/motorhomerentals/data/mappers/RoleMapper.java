package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Role;
import com.nordicmotorhome.motorhomerentals.data.entity.Staff;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class RoleMapper {

    public Role insert(Role role) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO roles (name) VALUES (?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, role.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();
            rs.next();

            int id = rs.getInt(1);
            role.setID(id);
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Role get(int id) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM roles WHERE id = ?";
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

    public Role update(Role model) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE rentals WHERE id = ?, name = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.setString(2, model.getName());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Role model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM roles WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Role load(ResultSet rs) throws SQLException, NoSuchEntityException {
        return new Role(
                rs.getString("name"),
                rs.getInt("id")
        );
    }
}
