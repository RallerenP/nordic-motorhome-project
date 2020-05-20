package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.MotorhomeModel;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class MotorhomeModelMapperTest {
    @Test
    void getTest() throws SQLException {
        // Arrange
        Connection conn = DBManager.getConnection();
        MotorhomeModelMapper mapper = new MotorhomeModelMapper();

        String SQL = "INSERT INTO motorhome_models (name, beds, price) VALUES ('TestModel', 800, 99.99)";
        PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        int id = rs.getInt(1);

        // Act
        MotorhomeModel mh = mapper.get(id);

        SQL = "DELETE FROM motorhome_models WHERE id = " + id;
        ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();

        // Assert
        assertEquals("TestModel", mh.getName());
        assertEquals(id, mh.getID());
        assertEquals(800, mh.getBeds());
        assertEquals(99.99, mh.getPrice());
    }

    @Test
    void insertTest() {
        // Arrange
        Connection conn = DBManager.getConnection();
        MotorhomeModelMapper mapper = new MotorhomeModelMapper();

        MotorhomeModel model = new MotorhomeModel(0, "TestModel", 200, 2000);

        // Act
        mapper.insert(model);
    }
}