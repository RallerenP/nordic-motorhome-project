package com.nordicmotorhome.motorhomerentals.domain;

import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.Message;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

// AUTHOR : RAP
public interface IDomainFacade {
    Message getAccessory(int id);
    Message getAllAccessories();
    Message getAllAccessories(List<Integer> ids);

    Message registerStaff(String email, String pw, String firstname, String lastName, int role_id);
    Message loginStaff(String email, String pw);

    Message createCustomer(String firstName, String lastName, int number, String email, String cpr, StaffModel auth);
    Message findCustomer(String cpr);
    Message updateCustomer(int ID, String firstName, String lastName, String email, int phone);

    Message searchMotorhome(int beds);
    Message searchMotorhome(int beds, LocalDate startDate, LocalDate endDate);

    Message createRentalAccessory(int rentalId, int accessoryId, int amount);
    Message cancelAccessoryRental(int id);

    Message createRental(int customerId, LocalDate startDate, LocalDate endDate, int motorhomeId, int pickupDistance, int deliveryDistance);
    Message getRental(int id);

    Message getBillingInfo(HashMap<AccessoryModel, Integer> accessories, int motorhomeId, LocalDate startDate, LocalDate endDate);
    Message findRentals();
    Message cancelRental(int id);
    Message getRentalEndingFees(int id, boolean fuelNeeded, int kmDrive);
}
