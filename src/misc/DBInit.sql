DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS accessories_stock;
DROP TABLE IF EXISTS rental_accessories;
DROP TABLE IF EXISTS accessory;
DROP TABLE IF EXISTS rentals;
DROP TABLE IF EXISTS motorhome;
DROP TABLE IF EXISTS motorhome_models;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    tlf integer(8) NOT NULL,
    email varchar(255) NOT NULL,
    cpr char(10) NOT NULL UNIQUE
);

CREATE TABLE motorhome_models (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    beds INTEGER NOT NULL,
    price DECIMAL(10,2)
);

CREATE TABLE motorhome (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    model_id INTEGER NOT NULL,
    km_driven INTEGER NOT NULL,
	cleaned BIT NOT NULL,
    serviced BIT NOT NULL,
    FOREIGN KEY (model_id) REFERENCES motorhome_models(ID)
);

CREATE TABLE rentals (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    start_km INTEGER NOT NULL,
	end_km INTEGER,
    fuel_needed BIT,
    customer_id INTEGER NOT NULL,
    motorhome_id INTEGER NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(ID),
    FOREIGN KEY (motorhome_id) REFERENCES motorhome(ID)
);

CREATE TABLE accessory (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    price decimal(10,2) NOT NULL
);

CREATE TABLE rental_accessories (
	rental_id INTEGER NOT NULL,
    accessories_id INTEGER NOT NULL,
    PRIMARY KEY (rental_id, accessories_id),
    FOREIGN KEY (rental_id) REFERENCES rentals(ID),
    FOREIGN KEY (accessories_id) REFERENCES accessory(ID)
);

CREATE TABLE accessories_stock (
	accessories_id INTEGER PRIMARY KEY,
    amount INTEGER NOT NULL,
    FOREIGN KEY (accessories_id) REFERENCES accessory(ID)
);

CREATE TABLE roles (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE staff (
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(ID)
)

