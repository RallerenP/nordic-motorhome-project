package com.nordicmotorhome.motorhomerentals.data;

import com.nordicmotorhome.motorhomerentals.data.entity.*;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;

public interface IDataFacade {
    // Accessories
    public AccessoryEntity getAccessoryById(int id) throws NoSuchEntityException;
    public List<AccessoryEntity> getAllAccessories() throws NoSuchEntityException;
    public AccessoryEntity createAccessory(AccessoryEntity entity);
    public void deleteAccessory(AccessoryEntity model);
    public AccessoryEntity saveAccessory(AccessoryEntity entity);
    public AccessoryEntity findOneAccessory(String key, String value) throws NoSuchEntityException;
    public AccessoryEntity findOneAccessory(String key, int value) throws NoSuchEntityException;

    // AccessoryStock
    public AccessoryStockEntity getAccessoryStockById(int id) throws NoSuchEntityException;
    public List<AccessoryStockEntity> getAllAccessoryStock() throws NoSuchEntityException;
    public AccessoryStockEntity createAccessoryStock(AccessoryStockEntity entity);
    public void deleteAccessoryStock(AccessoryStockEntity entity);
    public AccessoryStockEntity saveAccessoryStock(AccessoryStockEntity entity);
    public AccessoryStockEntity findOneAccessoryStock(String key, String value) throws NoSuchEntityException;
    public AccessoryStockEntity findOneAccessoryStock(String key, int value) throws NoSuchEntityException;

    // Customer
    public CustomerEntity getCustomerById(int id) throws NoSuchEntityException;
    public List<CustomerEntity> getAllCustomers() throws NoSuchEntityException;
    public CustomerEntity createCustomer(CustomerEntity entity);
    public void deleteCustomer(CustomerEntity entity);
    public CustomerEntity saveCustomer(CustomerEntity entity);
    public CustomerEntity findOneCustomer(String key, String value) throws NoSuchEntityException;
    public CustomerEntity findOneCustomer(String key, int value) throws NoSuchEntityException;

    // MotorhomeModel
    public MotorhomeModelEntity getMotorhomeModelById(int id) throws NoSuchEntityException;
    public List<MotorhomeModelEntity> getAllMotorhomeModels() throws NoSuchEntityException;
    public MotorhomeModelEntity createMotorhomeModel(MotorhomeModelEntity entity);
    public void deleteMotorhomeModel(MotorhomeModelEntity entity);
    public MotorhomeModelEntity saveMotorhomeModel(MotorhomeModelEntity entity);
    public MotorhomeModelEntity findOneMotorhomeModel(String key, String value) throws NoSuchEntityException;
    public MotorhomeModelEntity findOneMotorhomeModel(String key, int value) throws NoSuchEntityException;

    // Motorhome
    public MotorhomeEntity getMotorhomeById(int id) throws NoSuchEntityException;
    public List<MotorhomeEntity> getAllMotorhomes() throws NoSuchEntityException;
    public MotorhomeEntity createMotorhome(MotorhomeEntity entity);
    public void deleteMotorhome(MotorhomeEntity entity);
    public MotorhomeEntity saveMotorhome(MotorhomeEntity entity);
    public MotorhomeEntity findOneMotorhome(String key, String value) throws NoSuchEntityException;
    public MotorhomeEntity findOneMotorhome(String key, int value) throws NoSuchEntityException;

    // TODO: RentalAccessories

    // Rental
    public RentalEntity getRentalById(int id) throws NoSuchEntityException;
    public List<RentalEntity> getAllRentals() throws NoSuchEntityException;
    public RentalEntity createRental(RentalEntity entity);
    public void deleteRental(RentalEntity entity);
    public RentalEntity saveRental(RentalEntity entity);
    public RentalEntity findOneRental(String key, String value) throws NoSuchEntityException;
    public RentalEntity findOneRental(String key, int value) throws NoSuchEntityException;

    // Role
    public RoleEntity getRoleById(int id) throws NoSuchEntityException;
    public List<RoleEntity> getAllRoles() throws NoSuchEntityException;
    public RoleEntity createRole(RoleEntity entity);
    public void deleteRole(RoleEntity entity);
    public RoleEntity saveRole(RoleEntity entity);
    public RoleEntity findOneRole(String key, String value) throws NoSuchEntityException;
    public RoleEntity findOneRole(String key, int value) throws NoSuchEntityException;

    // Staff
    public StaffEntity getStaffById(int id) throws NoSuchEntityException;
    public List<StaffEntity> getAllStaff() throws NoSuchEntityException;
    public StaffEntity createStaff(StaffEntity entity);
    public void deleteStaff(StaffEntity entity);
    public StaffEntity saveStaff(StaffEntity entity);
    public StaffEntity findOneStaff(String key, String value) throws NoSuchEntityException;
    public StaffEntity findOneStaff(String key, int value) throws NoSuchEntityException;
    }
