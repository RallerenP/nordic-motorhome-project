package com.nordicmotorhome.motorhomerentals.data.repositories;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.domain.entities.AccessoryEntity;
import com.nordicmotorhome.motorhomerentals.domain.entities.MotorhomeEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorhomeRepository implements IRepository<MotorhomeEntity> {
    @Override
    public MotorhomeEntity getById(int id) throws NoSuchEntityException {
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

    @Override
    public List<MotorhomeEntity> getAll() throws NoSuchEntityException {
        try {
            ArrayList<MotorhomeEntity> entities = new ArrayList<>();

            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();

            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public MotorhomeEntity create(MotorhomeEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getMotorhomeModelEntity().getID());
            ps.setInt(2, entity.getKilometersDriven());
            ps.setBoolean(3, entity.isCleaned());
            ps.setBoolean(4, entity.isServiced());

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
    public void delete(MotorhomeEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "DELETE FROM motorhomes WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, entity.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MotorhomeEntity save(MotorhomeEntity entity) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE motorhomes SET model_id = ?, km_driven = ?, cleaned = ?, serviced = ? WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, entity.getMotorhomeModelEntity().getID());
            ps.setInt(2, entity.getKilometersDriven());
            ps.setBoolean(3, entity.isCleaned());
            ps.setBoolean(4, entity.isServiced());
            ps.setInt(5, entity.getID());

            ps.executeUpdate();

            return getById(entity.getID());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MotorhomeEntity findOne(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes WHERE " + key + " = ?";
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
    public MotorhomeEntity findOne(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes WHERE " + key + " = ?";
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

    @Override
    public ArrayList<MotorhomeEntity> findAll(String key, String value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<MotorhomeEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<MotorhomeEntity> findAll(String key, int value) throws NoSuchEntityException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM motorhomes WHERE " + key + " = ?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, value);

            ResultSet rs = ps.executeQuery();

            ArrayList<MotorhomeEntity> entities = new ArrayList<>();

            while(rs.next()) entities.add(load(rs));

            if (entities.size() == 0) throw new NoSuchEntityException();
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MotorhomeEntity load(ResultSet rs) throws SQLException, NoSuchEntityException {
        return new MotorhomeEntity(
                rs.getInt("ID"),
                new MotorhomeModelRepository().getById(rs.getInt("model_id")),
                rs.getInt("km_driven"),
                rs.getBoolean("cleaned"),
                rs.getBoolean("serviced")

        );
    }
}
