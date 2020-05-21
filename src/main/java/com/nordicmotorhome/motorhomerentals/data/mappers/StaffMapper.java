package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Staff;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class StaffMapper {

    public Staff insert(Staff staff) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO staff (first_name, last_name, email, password, role_id) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getEmail());
            ps.setString(4, staff.getPassword());
            ps.setInt(5, staff.getRole().getID());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();
            rs.next();

            int id = rs.getInt(1);
            staff.setID(id);
            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Staff get(int id) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE id = ?";
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

    public Staff update(Staff model) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE staff WHERE id = ?, first_name = ?, last_name = ?, email = ?, password = ?, role_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.setString(2, model.getFirstName());
            ps.setString(3, model.getLastName());
            ps.setString(4, model.getEmail());
            ps.setString(5, model.getPassword());
            ps.setInt(6, model.getRole().getID());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Staff model) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM staff WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, model.getID());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Staff load(ResultSet rs) throws SQLException, NoSuchEntityException {
        RoleMapper rm = new RoleMapper();
        return new Staff(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rm.get(rs.getInt("role_id")),
                rs.getString("email"),
                rs.getString("password")

        );
    }
}
