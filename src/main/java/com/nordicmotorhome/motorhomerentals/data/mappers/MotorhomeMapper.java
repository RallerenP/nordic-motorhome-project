package com.nordicmotorhome.motorhomerentals.data.mappers;

import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.entity.Motorhome;

import java.sql.Connection;

public class MotorhomeMapper {
    public MotorhomeMapper() {

    }

    public Motorhome insert(Motorhome motorhome) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO motorhomes ("
        }
    }
}
