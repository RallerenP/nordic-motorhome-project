package com.nordicmotorhome.motorhomerentals;

import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.UI.model.RentalModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DBManager;
import com.nordicmotorhome.motorhomerentals.data.repositories.StaffRepository;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.services.AuthenticationService;
import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import com.nordicmotorhome.motorhomerentals.domain.services.RentalService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DBSetup {
    public void setup() {
        Connection conn = DBManager.getConnection();

        try {
            File f = new File("SetupHasBeenRun");

            if (f.exists()) {
                // Setup has been run, abort
                return;
            }

            // Setup has not been run
            new FileOutputStream(f).close();

            Statement stmt = conn.createStatement();

            // region SCHEMA SETUP

            stmt.addBatch("DROP TABLE IF EXISTS staff;");
            stmt.addBatch("DROP TABLE IF EXISTS roles;");
            stmt.addBatch("DROP TABLE IF EXISTS accessories_stock;");
            stmt.addBatch("DROP TABLE IF EXISTS rental_accessories;");
            stmt.addBatch("DROP TABLE IF EXISTS accessories;");
            stmt.addBatch("DROP TABLE IF EXISTS rentals;");
            stmt.addBatch("DROP TABLE IF EXISTS motorhomes;");
            stmt.addBatch("DROP TABLE IF EXISTS motorhome_models");
            stmt.addBatch("DROP TABLE IF EXISTS customers;");

            stmt.addBatch(
                    "CREATE TABLE customers (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "first_name varchar(255) NOT NULL," +
                            "last_name varchar(255) NOT NULL," +
                            "tlf integer(8) NOT NULL," +
                            "email varchar(255) NOT NULL," +
                            "cpr char(10) NOT NULL UNIQUE" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE motorhome_models (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(255) NOT NULL," +
                            "beds INTEGER NOT NULL," +
                            "price DECIMAL(10,2)" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE motorhomes (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "model_id INTEGER NOT NULL," +
                            "km_driven INTEGER NOT NULL," +
                            "cleaned BIT NOT NULL," +
                            "serviced BIT NOT NULL," +
                            "FOREIGN KEY (model_id) REFERENCES motorhome_models(ID)" +
                            ");"
            );

            stmt.addBatch("" +
                    "CREATE TABLE rentals (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "start_date DATE NOT NULL," +
                            "end_date DATE NOT NULL," +
                            "start_km INTEGER NOT NULL," +
                            "end_km INTEGER," +
                            "fuel_needed BIT," +
                            "customer_id INTEGER NOT NULL," +
                            "motorhome_id INTEGER NOT NULL," +
                            "pickup_distance INTEGER," +
                            "delivery_distance INTEGER," +
                            "FOREIGN KEY (customer_id) REFERENCES customers(ID)," +
                            "FOREIGN KEY (motorhome_id) REFERENCES motorhomes(ID)" +
                            ");");

            stmt.addBatch(
                    "CREATE TABLE accessories (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "name varchar(255) NOT NULL," +
                            "price decimal(10,2) NOT NULL" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE rental_accessories (" +
                            "rental_id INTEGER NOT NULL," +
                            "accessories_id INTEGER NOT NULL," +
                            "amount INTEGER DEFAULT 0," +
                            "PRIMARY KEY (rental_id, accessories_id)," +
                            "FOREIGN KEY (rental_id) REFERENCES rentals(ID)," +
                            "FOREIGN KEY (accessories_id) REFERENCES accessories(ID)" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE accessories_stock (" +
                            "accessories_id INTEGER PRIMARY KEY," +
                            "amount INTEGER NOT NULL," +
                            "FOREIGN KEY (accessories_id) REFERENCES accessories(ID)" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE roles (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "name VARCHAR(255)" +
                            ");"
            );

            stmt.addBatch(
                    "CREATE TABLE staff (" +
                            "ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                            "first_name VARCHAR(255) NOT NULL," +
                            "last_name VARCHAR(255) NOT NULL," +
                            "email VARCHAR(255) NOT NULL UNIQUE," +
                            "password VARCHAR(255) NOT NULL," +
                            "role_id INTEGER NOT NULL," +
                            "FOREIGN KEY (role_id) REFERENCES roles(ID)" +
                            ")"
            );

            //endregion

            //region ROLES DATA

            stmt.addBatch(
                    "INSERT INTO roles (name) VALUES ('SALES_ASSISTANT')"
            );
            stmt.addBatch(
                    "INSERT INTO roles (name) VALUES ('MECHANIC')"
            );
            stmt.addBatch(
                    "INSERT INTO roles (name) VALUES ('CLEANER')"
            );
            stmt.addBatch(
                    "INSERT INTO roles (name) VALUES ('BOOK_KEEPER')"
            );

            //endregion

            //region MOTOR HOME MODELS DUMMY DATA

            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('HammerWar60K', 6, 200)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('HammerWar40K', 4, 135)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('HammerWar20K', 2, 70)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('WarHound', 2, 270)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('Knight', 3, 200)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('BareEtSkur', 6, 70)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('ImperialTitan', 5, 1350)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhome_models (name, beds, price) VALUES ('BrianVogn', 2, 80)"
            );

            //endregion

            //region MOTORHOMES DUMMY DATA

            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (1, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (1, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (1, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (1, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (2, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (2, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (2, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (2, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (3, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (3, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (3, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (3, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (4, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (4, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (4, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (4, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (5, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (5, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (5, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (5, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (6, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (6, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (6, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (6, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (7, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (7, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (7, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (7, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (8, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (8, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (8, 0, 1, 1)"
            );
            stmt.addBatch(
                    "INSERT INTO motorhomes (model_id, km_driven, cleaned, serviced) VALUES (8, 0, 1, 1)"
            );

            //endregion

            //region ACCESSORIES DUMMY DATA

            stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Stole og borde', 3.5)");
            stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Cykelholder', 1.5)");
            stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Sengetøj', 4)");
            stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Børnestol', 2)");
            stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Gasgrill', 2)");

            stmt.addBatch("INSERT INTO accessories_stock (accessories_id, amount) VALUES (1, 10)");
            stmt.addBatch("INSERT INTO accessories_stock (accessories_id, amount) VALUES (2, 22)");
            stmt.addBatch("INSERT INTO accessories_stock (accessories_id, amount) VALUES (3, 120)");
            stmt.addBatch("INSERT INTO accessories_stock (accessories_id, amount) VALUES (4, 10)");
            stmt.addBatch("INSERT INTO accessories_stock (accessories_id, amount) VALUES (5, 32)");

            stmt.executeBatch();

            //endregion

            //region STAFF DUMMY DATA

            AuthenticationService as = new AuthenticationService();

            as.register("bob@bob.dk", "1234", "Bob", "Bob", 1);
            as.register("hans@hansen.dk", "password", "Hans", "Hansen", 1);
            as.register("søren@pind.dk", "p1nd", "Søren", "Pind", 1);
            as.register("ib@ippersen.dk", "password1", "Ib", "Ippersen", 1);

            as.register("brian@brandsen.dk", "BrainIld", "Brian", "Brandsen", 2);

            as.register("fie@laursen.dk", "SugarDating2", "Fie", "Laursen", 3);
            as.register("martin@laursen.dk", "MartinDating2", "Martin", "Laursen", 3);

            as.register("mie@laursen.dk", "4321", "Mie", "Laursen", 4);

            //endregion

            //region RENTAL DUMMY DATA
            CustomerService cs = new CustomerService();
            RentalService rs = new RentalService();

            StaffRepository sr = new StaffRepository();
            try {
                StaffModel sm = new StaffEntityModelMapper().mapToModel(sr.getById(1));
                CustomerModel ce = (CustomerModel) cs.create("Bob", "Bob", 88888888, "bob@bob.dk", "2605204555", sm).getContent();

                RentalModel rm = (RentalModel) rs.create(ce.getID(), LocalDate.now(), LocalDate.now().plusWeeks(2), 5, 0, 0).getContent();

            } catch (NoSuchEntityException e) {
                e.printStackTrace();
            }
            //endregion

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
