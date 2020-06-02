package com.nordicmotorhome.motorhomerentals.domain;

import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.services.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class DomainFacadeImpl implements IDomainFacade {
    private static final DomainFacadeImpl instance = new DomainFacadeImpl();
    public static DomainFacadeImpl getInstance() { return instance; };

    private DomainFacadeImpl() {};

    AccessoryService as = new AccessoryService();
    AuthenticationService auths = new AuthenticationService();
    CustomerService cs = new CustomerService();
    MotorhomeService ms = new MotorhomeService();
    RentalAccessoryService ras = new RentalAccessoryService();
    RentalService rs = new RentalService();

    @Override
    public Message getAccessory(int id) {
        return as.getAccessory(id);
    }

    @Override
    public Message getAllAccessories() {
        return as.getAllAccessories();
    }

    @Override
    public Message getAllAccessories(List<Integer> ids) {
        return as.getAllAccessories(ids);
    }

    @Override
    public Message registerStaff(String email, String pw, String firstName, String lastName, int role_id) {
        return auths.register(email, pw, firstName, lastName, role_id);
    }

    @Override
    public Message loginStaff(String email, String pw) {
        return auths.login(email, pw);
    }

    @Override
    public Message createCustomer(String firstName, String lastName, int number, String email, String cpr, StaffModel auth) {
        return cs.create(firstName, lastName, number, email, cpr, auth);
    }

    @Override
    public Message findCustomer(String cpr) {
        return cs.findCustomer(cpr);
    }

    @Override
    public Message updateCustomer(int ID, String firstName, String lastName, String email, int phone) {
        return cs.update(ID, firstName, lastName, email, phone);
    }

    @Override
    public Message searchMotorhome(int beds) {
        return ms.searchMotorhomes(beds);
    }

    @Override
    public Message searchMotorhome(int beds, LocalDate startDate, LocalDate endDate) {
        return ms.searchMotorhomes(beds, startDate, endDate);
    }

    @Override
    public Message createRentalAccessory(int rentalId, int accessoryId, int amount) {
        return ras.create(rentalId, accessoryId, amount);
    }

    @Override
    public Message cancelAccessoryRental(int id) {
        return ras.cancelAccessoryRental(id);
    }

    @Override
    public Message createRental(int customerId, LocalDate startDate, LocalDate endDate, int motorhomeId, int pickupDistance, int deliveryDistance) {
        return rs.create(customerId, startDate, endDate, motorhomeId, pickupDistance, deliveryDistance);
    }

    @Override
    public Message getRental(int id) {
        return rs.getRental(id);
    }

    @Override
    public Message getBillingInfo(HashMap<AccessoryModel, Integer> accessories, int motorhomeId, LocalDate startDate, LocalDate endDate) {
        return rs.getBillingInfo(accessories, motorhomeId, startDate, endDate);
    }

    @Override
    public Message findRentals() {
        return rs.findRentals();
    }

    @Override
    public Message cancelRental(int id) {
        return rs.cancelRental(id);
    }

    @Override
    public Message getRentalEndingFees(int id, boolean fuelNeeded, int kmDrive) {
        rs.setRentalEndKilometers(id, kmDrive);
        rs.setRentalFuelNeeded(id, fuelNeeded);
        return rs.calculateFees(id);
    }
}
