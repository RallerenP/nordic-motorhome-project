package com.nordicmotorhome.motorhomerentals.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
                70,
                rentalAccessoryEntities
        );
        sengetøjRental.setRental(rentalEntity);
        grillRental.setRental(rentalEntity);

        // Formula = ((basePrice * days) * seasonalMult) + fuelNeeded + (((grillPrice * amount) * days) + ((bedLinnenPrice * amount) * days) + floor(AVG_KM_OVER) + (deliveryDistance * 0.70) + (pickupDistance * 0.70)
        // value = ((1000 * 14) * 1.6) + 70 + ((15 * 1) * 14) + ((30 * 4) * 14) + 171 + (70 * 0.7) + (70 * 0.7) = 24629;
        double expected = 24629;

        // Act
        double found = rentalEntity.calculateFees();

        // Assert
        assertEquals(expected, found);
    }
}