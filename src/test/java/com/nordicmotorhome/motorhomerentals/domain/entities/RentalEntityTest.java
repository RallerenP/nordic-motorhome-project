package com.nordicmotorhome.motorhomerentals.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RentalEntityTest {

    @Test
    void calculateFees() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "HammerWar40K", 4, 1000);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        // stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Gasgrill', 15)");
        //stmt.addBatch("INSERT INTO accessories (name, price) VALUES ('Sengetøj', 30)");
        AccessoryEntity grill = new AccessoryEntity(0, "Gasgrill", 15);
        AccessoryEntity sengetøj = new AccessoryEntity(0, "Sengetøj", 30);

        RentalAccessoryEntity grillRental = new RentalAccessoryEntity(null, grill, 1);
        RentalAccessoryEntity sengetøjRental = new RentalAccessoryEntity(null, sengetøj, 4);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        rentalAccessoryEntities.add(grillRental);
        rentalAccessoryEntities.add(sengetøjRental);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = ((basePrice * days) * seasonalMult) + fuelNeeded + (((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days) + floor(AVG_KM_OVER) + (deliveryDistance * 0.70) + (pickupDistance * 0.70)
        // value = ((1000 * 14) * 1.6) + 70 + ((15 * 1) * 14) + ((30 * 4) * 14) + (8000 - (14 * 400)) + (70 * 0.7) + (70 * 0.7) = 26858;
        double expected = 26858;

        // Act
        double found = rentalEntity.calculateFees();

        // Assert
        assertEquals(expected, found);
    }

    @Test
    void calculateCancellationFeesOnDay() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "HammerWar40K", 4, 1000);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        AccessoryEntity grill = new AccessoryEntity(0, "Gasgrill", 15);
        AccessoryEntity sengetøj = new AccessoryEntity(0, "Sengetøj", 30);

        RentalAccessoryEntity grillRental = new RentalAccessoryEntity(null, grill, 1);
        RentalAccessoryEntity sengetøjRental = new RentalAccessoryEntity(null, sengetøj, 4);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        rentalAccessoryEntities.add(grillRental);
        rentalAccessoryEntities.add(sengetøjRental);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = (((basePrice * days) * seasonalMultiplier) + ((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days)) * daysLeftMultiplier
        // Value = (((1000 * 14) * 1.6) + ((15 * 1) * 14) + ((30 * 4) * 14)) * 0.95 = 23075.5
        double expected = 23075.5;

        // Act
        double found = rentalEntity.calculateCancellationFees();

        // Assert
        assertEquals(expected, found);

    }

    @Test
    void calculateCancellationFees1Week() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "HammerWar40K", 4, 1000);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        AccessoryEntity grill = new AccessoryEntity(0, "Gasgrill", 15);
        AccessoryEntity sengetøj = new AccessoryEntity(0, "Sengetøj", 30);

        RentalAccessoryEntity grillRental = new RentalAccessoryEntity(null, grill, 1);
        RentalAccessoryEntity sengetøjRental = new RentalAccessoryEntity(null, sengetøj, 4);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        rentalAccessoryEntities.add(grillRental);
        rentalAccessoryEntities.add(sengetøjRental);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now().plusWeeks(1),
                LocalDate.now().plusWeeks(10),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = (((basePrice * days) * seasonalMultiplier) + ((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days)) * daysLeftMultiplier
        // Value = (((1000 * 63) * 1.6) + ((15 * 1) * 63) + ((30 * 4) * 63)) * 0.8 = 87444
        double expected = 87444;

        // Act
        double found = rentalEntity.calculateCancellationFees();

        // Assert
        assertEquals(expected, found);

    }

    @Test
    void calculateCancellationFees3Weeks() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "HammerWar40K", 4, 1000);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        AccessoryEntity grill = new AccessoryEntity(0, "Gasgrill", 15);
        AccessoryEntity sengetøj = new AccessoryEntity(0, "Sengetøj", 30);

        RentalAccessoryEntity grillRental = new RentalAccessoryEntity(null, grill, 1);
        RentalAccessoryEntity sengetøjRental = new RentalAccessoryEntity(null, sengetøj, 4);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        rentalAccessoryEntities.add(grillRental);
        rentalAccessoryEntities.add(sengetøjRental);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now().plusWeeks(3),
                LocalDate.now().plusWeeks(10),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = (((basePrice * days) * seasonalMultiplier) + ((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days)) * daysLeftMultiplier
        // Value = (((1000 * 49) * 1.6) + ((15 * 1) * 49) + ((30 * 4) * 49)) * 0.5 = 42507.5
        double expected = 42507.5;

        // Act
        double found = rentalEntity.calculateCancellationFees();

        // Assert
        assertEquals(expected, found);

    }

    @Test
    void calculateCancellationFees8Weeks() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "HammerWar40K", 4, 1000);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        AccessoryEntity grill = new AccessoryEntity(0, "Gasgrill", 15);
        AccessoryEntity sengetøj = new AccessoryEntity(0, "Sengetøj", 30);

        RentalAccessoryEntity grillRental = new RentalAccessoryEntity(null, grill, 1);
        RentalAccessoryEntity sengetøjRental = new RentalAccessoryEntity(null, sengetøj, 4);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        rentalAccessoryEntities.add(grillRental);
        rentalAccessoryEntities.add(sengetøjRental);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now().plusWeeks(8),
                LocalDate.now().plusWeeks(10),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = (((basePrice * days) * seasonalMultiplier) + ((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days)) * daysLeftMultiplier
        // Value = (((1000 * 14) * 1.6) + ((15 * 1) * 14) + ((30 * 4) * 14)) * 0.2 = 4858
        double expected = 4858;

        // Act
        double found = rentalEntity.calculateCancellationFees();

        // Assert
        assertEquals(expected, found);

    }

    @Test
    void calculateFees200Fee() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "BareEtSkur", 4, 500);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        ArrayList<RentalAccessoryEntity> rentalAccessoryEntities = new ArrayList<>();

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now().plusDays(51),
                LocalDate.now().plusDays(52),
                0,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        rentalEntity.setAccessoryEntities(rentalAccessoryEntities);

        // Formula = (((basePrice * days) * seasonalMultiplier) * daysLeftMultiplier
        // Value = (((500 * 1) * 1.6) * 0.2 = 160
        double expected = 200;

        // Act
        double found = rentalEntity.calculateCancellationFees();

        // Assert
        assertEquals(expected, found);
    }


    @Test
    void getMileagePrice() {
        //Arrange
        MotorhomeModelEntity motorhomeModelEntity = new MotorhomeModelEntity(0, "BareEtSkur", 4, 500);
        MotorhomeEntity motorhomeEntity = new MotorhomeEntity(0, motorhomeModelEntity, 5600, true, true);

        RentalEntity rentalEntity = new RentalEntity(
                0,
                LocalDate.now().plusDays(51),
                LocalDate.now().plusDays(52),
                5600,
                8000,
                true,
                null,
                motorhomeEntity,
                70,
                70
        );
        // Formula = AVG KM OVER - 400
        double expected = 2000;

        // Act
        double found = rentalEntity.getMileagePrice();

        // Assert
        assertEquals(expected, found);
    }
}