package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.StaffEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// AUTHOR: RAP, NKJ, AML, ME
// Used for converting data from database to entities in our program
public class StaffRepository implements IRepository<StaffEntity> {

    @Override
    public StaffEntity getById(int id) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE id = ?";
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
    public List<StaffEntity> getAll() throws NoSuchEntityException {
        return null;
    }

    @Override
    public StaffEntity create(StaffEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO staff (first_name, last_name, email, password, role_id) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setInt(5, entity.getRoleEntity().getID());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            return getById(id);
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(StaffEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM staff WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, entity.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StaffEntity save(StaffEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE staff SET first_name = ?, last_name = ?, email = ?, role_id = ?, password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getRoleEntity().getID());
            ps.setString(5, entity.getPassword());
            ps.setInt(6, entity.getID());

            ps.executeUpdate();

            return getById(entity.getID());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StaffEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE " + key + " = ?";
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
    public StaffEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE " + key + " = ?";
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
    public List<StaffEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<StaffEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StaffEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM staff WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<StaffEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public StaffEntity load(ResultSet rs) throws SQLException, NoSuchEntityException {
        RoleRepository rr = new RoleRepository();
        return new StaffEntity(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rr.getById(rs.getInt("role_id")),
                rs.getString("email"),
                rs.getString("password")
        );
    }
}
