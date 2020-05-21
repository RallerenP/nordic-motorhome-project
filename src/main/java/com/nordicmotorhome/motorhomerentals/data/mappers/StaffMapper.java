package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Staff;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;

public class StaffMapper {

    public Staff insert(Staff staff, String hashedPw) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO staff (first_name, last_name, email, password, role_id) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, staff.getFirstName());
            ps.setString(2, staff.getLastName());
            ps.setString(3, staff.getEmail());
            ps.setString(4, hashedPw);
            ps.setInt(5, staff.getRole().getID());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next()) throw new NoSuchEntityException();

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
            String SQL = "SELECT ID, first_name, last_name, role_id, email FROM staff WHERE id = ?";
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

    public Staff getByEmail(String email) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT ID, first_name, last_name, role_id, email FROM staff WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();
            return this.load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPwById(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT password FROM staff WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new NoSuchEntityException();
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Staff update(Staff model) throws NoSuchEntityException{
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE staff SET first_name = ?, last_name = ?, email = ?, role_id = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, model.getFirstName());
            ps.setString(2, model.getLastName());
            ps.setString(3, model.getEmail());
            ps.setInt(4, model.getRole().getID());
            ps.setInt(5, model.getID());

            ps.executeUpdate();

            return this.get(model.getID());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updatePw(int id, String hashedPw) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE staff SET password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, hashedPw);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                rs.getString("email")
        );
    }
}
