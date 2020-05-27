package com.nordicmotorhome.motorhomerentals.data;

import com.nordicmotorhome.motorhomerentals.domain.entities.*;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;

interface IDataFacade {
    // Accessories
    AccessoryEntity getAccessoryById(int id) throws NoSuchEntityException;
    List<AccessoryEntity> getAllAccessories() throws NoSuchEntityException;
    AccessoryEntity createAccessory(AccessoryEntity entity);
    void deleteAccessory(AccessoryEntity model);
    AccessoryEntity saveAccessory(AccessoryEntity entity);
    AccessoryEntity findOneAccessory(String key, String value) throws NoSuchEntityException;
    AccessoryEntity findOneAccessory(String key, int value) throws NoSuchEntityException;
    List<AccessoryEntity> findAllAccessories(String key, String value) throws NoSuchEntityException;
    List<AccessoryEntity> findAllAccessories(String key, int value) throws NoSuchEntityException;

    // AccessoryStock
    AccessoryStockEntity getAccessoryStockById(int id) throws NoSuchEntityException;
    List<AccessoryStockEntity> getAllAccessoryStock() throws NoSuchEntityException;
    AccessoryStockEntity createAccessoryStock(AccessoryStockEntity entity);
    void deleteAccessoryStock(AccessoryStockEntity entity);
    AccessoryStockEntity saveAccessoryStock(AccessoryStockEntity entity);
    AccessoryStockEntity findOneAccessoryStock(String key, String value) throws NoSuchEntityException;
    AccessoryStockEntity findOneAccessoryStock(String key, int value) throws NoSuchEntityException;
    List<AccessoryStockEntity> findAllAccessoryStock(String key, String value) throws NoSuchEntityException;
    List<AccessoryStockEntity> findAllAccessoryStock(String key, int value) throws NoSuchEntityException;

    // Customer
    CustomerEntity getCustomerById(int id) throws NoSuchEntityException;
    List<CustomerEntity> getAllCustomers() throws NoSuchEntityException;
    CustomerEntity createCustomer(CustomerEntity entity);
    void deleteCustomer(CustomerEntity entity);
    CustomerEntity saveCustomer(CustomerEntity entity);
    CustomerEntity findOneCustomer(String key, String value) throws NoSuchEntityException;
    CustomerEntity findOneCustomer(String key, int value) throws NoSuchEntityException;
    List<CustomerEntity> findAllCustomers(String key, String value) throws NoSuchEntityException;
    List<CustomerEntity> findAllCustomers(String key, int value) throws NoSuchEntityException;

    // MotorhomeModel
    MotorhomeModelEntity getMotorhomeModelById(int id) throws NoSuchEntityException;
    List<MotorhomeModelEntity> getAllMotorhomeModels() throws NoSuchEntityException;
    MotorhomeModelEntity createMotorhomeModel(MotorhomeModelEntity entity);
    void deleteMotorhomeModel(MotorhomeModelEntity entity);
    MotorhomeModelEntity saveMotorhomeModel(MotorhomeModelEntity entity);
    MotorhomeModelEntity findOneMotorhomeModel(String key, String value) throws NoSuchEntityException;
    MotorhomeModelEntity findOneMotorhomeModel(String key, int value) throws NoSuchEntityException;
    List<MotorhomeModelEntity> findAllMotorhomeModels(String key, String value) throws NoSuchEntityException;
    List<MotorhomeModelEntity> findAllMotorhomeModels(String key, int value) throws NoSuchEntityException;

    // Motorhome
    MotorhomeEntity getMotorhomeById(int id) throws NoSuchEntityException;
    List<MotorhomeEntity> getAllMotorhomes() throws NoSuchEntityException;
    MotorhomeEntity createMotorhome(MotorhomeEntity entity);
    void deleteMotorhome(MotorhomeEntity entity);
    MotorhomeEntity saveMotorhome(MotorhomeEntity entity);
    MotorhomeEntity findOneMotorhome(String key, String value) throws NoSuchEntityException;
    MotorhomeEntity findOneMotorhome(String key, int value) throws NoSuchEntityException;
    List<MotorhomeEntity> findAllMotorhomes(String key, String value) throws NoSuchEntityException;
    List<MotorhomeEntity> findAllMotorhomes(String key, int value) throws NoSuchEntityException;

    // RentalAccessories
    RentalAccessoryEntity getRentalAccessoryById(int id) throws NoSuchEntityException;
    List<RentalAccessoryEntity> getAll() throws NoSuchEntityException;
    RentalAccessoryEntity createRentalAccessory(RentalAccessoryEntity entity);
    void deleteRentalAccessory(RentalAccessoryEntity entity);
    RentalAccessoryEntity saveRentalAccessory(RentalAccessoryEntity entity);
    RentalAccessoryEntity findOneRentalAccessory(String key, String value) throws NoSuchEntityException;
    RentalAccessoryEntity findOneRentalAccessory(String key, int value) throws NoSuchEntityException;
    List<RentalAccessoryEntity> findAllRentalAccessories(String key, String value) throws NoSuchEntityException;
    List<RentalAccessoryEntity> findAllRentalAccessories(String key, int value) throws NoSuchEntityException;

    // Rental
    RentalEntity getRentalById(int id) throws NoSuchEntityException;
    List<RentalEntity> getAllRentals() throws NoSuchEntityException;
    RentalEntity createRental(RentalEntity entity);
    void deleteRental(RentalEntity entity);
    RentalEntity saveRental(RentalEntity entity);
    RentalEntity findOneRental(String key, String value) throws NoSuchEntityException;
    RentalEntity findOneRental(String key, int value) throws NoSuchEntityException;
    List<RentalEntity> findAllRentals(String key, String value) throws NoSuchEntityException;
    List<RentalEntity> findAllRentals(String key, int value) throws NoSuchEntityException;

    // Role
    RoleEntity getRoleById(int id) throws NoSuchEntityException;
    List<RoleEntity> getAllRoles() throws NoSuchEntityException;
    RoleEntity createRole(RoleEntity entity);
    void deleteRole(RoleEntity entity);
    RoleEntity saveRole(RoleEntity entity);
    RoleEntity findOneRole(String key, String value) throws NoSuchEntityException;
    RoleEntity findOneRole(String key, int value) throws NoSuchEntityException;
    List<RoleEntity> findAllRoles(String key, String value) throws NoSuchEntityException;
    List<RoleEntity> findALlRoles(String key, int value) throws NoSuchEntityException;

    // Staff
    StaffEntity getStaffById(int id) throws NoSuchEntityException;
    List<StaffEntity> getAllStaff() throws NoSuchEntityException;
    StaffEntity createStaff(StaffEntity entity);
    void deleteStaff(StaffEntity entity);
    StaffEntity saveStaff(StaffEntity entity);
    StaffEntity findOneStaff(String key, String value) throws NoSuchEntityException;
    StaffEntity findOneStaff(String key, int value) throws NoSuchEntityException;
    List<StaffEntity> findAllStaff(String key, String value) throws NoSuchEntityException;
    List<StaffEntity> findAllStaff(String key, int value) throws NoSuchEntityException;

}
