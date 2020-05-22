package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.MotorhomeModelEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.List;

public class MotorhomeModelRepository implements IRepository<MotorhomeModelEntity> {
    @Override
    public MotorhomeModelEntity getById(int id) throws NoSuchEntityException {
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

    @Override
    public List<MotorhomeModelEntity> getAll() throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeModelEntity create(MotorhomeModelEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO motorhome_models (name, beds, price) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getBeds());
            ps.setDouble(1, entity.getPrice());
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
    public void delete(MotorhomeModelEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "DELETE FROM motorhome_models WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, entity.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MotorhomeModelEntity save(MotorhomeModelEntity entity) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "UPDATE motorhome_models SET name = ?, beds = ?, price = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getBeds());
            ps.setDouble(3, entity.getPrice());
            ps.setInt(4, entity.getID());

            ps.executeUpdate();

            return this.getById(entity.getID());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MotorhomeModelEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhome_models WHERE ? = ?";
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
    public MotorhomeModelEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhome_models WHERE ? = ?";
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

    public MotorhomeModelEntity load(ResultSet rs) throws SQLException {
        return new MotorhomeModelEntity(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("beds"),
                rs.getDouble("price")
                );
    }


}
