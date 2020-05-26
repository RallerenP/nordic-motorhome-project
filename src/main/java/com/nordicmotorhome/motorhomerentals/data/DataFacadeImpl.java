package com.nordicmotorhome.motorhomerentals.data;

import com.nordicmotorhome.motorhomerentals.data.entity.*;
import com.nordicmotorhome.motorhomerentals.data.repositories.*;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;

public class DataFacadeImpl implements  IDataFace {

    private final IRepository<AccessoryEntity> accessoryRepository = new AccessoryRepository();
    private final IRepository<AccessoryStockEntity> accessoryStockRepository = new AccessoryStockRepository();
    private final IRepository<CustomerEntity> customerRepository = new CustomerRepository();
    private final IRepository<MotorhomeEntity> motorhomeRepository = new MotorhomeRepository();
    private final IRepository<MotorhomeModelEntity> motorhomeModelRepository = new MotorhomeModelRepository();
    private final IRepository<RentalEntity> rentalRepository = new RentalRepository();
    private final IRepository<RoleEntity> roleRepository = new RoleRepository();
    private final IRepository<StaffEntity> staffRepository = new StaffRepository();

    @Override
    public AccessoryEntity getAccessoryById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<AccessoryEntity> getAllAccessory() throws NoSuchEntityException {
        return null;
    }

    @Override
    public AccessoryEntity createAccessory(AccessoryEntity entity) {
        return null;
    }

    @Override
    public void deleteAccessory(AccessoryEntity model) {

    }

    @Override
    public AccessoryEntity saveAccessory(AccessoryEntity entity) {
        return null;
    }

    @Override
    public AccessoryEntity findOneAccessory(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public AccessoryEntity findOneAccessory(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public AccessoryStockEntity getAccessoryStockById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<AccessoryStockEntity> getAlAccessoryStockl() throws NoSuchEntityException {
        return null;
    }

    @Override
    public AccessoryStockEntity createAccessoryStock(AccessoryStockEntity entity) {
        return null;
    }

    @Override
    public void deleteAccessoryStock(AccessoryStockEntity entity) {

    }

    @Override
    public AccessoryStockEntity saveAccessoryStock(AccessoryStockEntity entity) {
        return null;
    }

    @Override
    public AccessoryStockEntity findOneAccessoryStock(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public AccessoryStockEntity findOneAccessoryStock(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public CustomerEntity getCustomerById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<CustomerEntity> getAllCustomer() throws NoSuchEntityException {
        return null;
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity entity) {
        return null;
    }

    @Override
    public void deleteCustomer(CustomerEntity entity) {

    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity entity) {
        return null;
    }

    @Override
    public CustomerEntity findOneCustomer(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public CustomerEntity findOneCustomer(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeModelEntity getMotorhomeModelById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<MotorhomeModelEntity> getAllMotorhomeModel() throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeModelEntity createMotorhomeModel(MotorhomeModelEntity entity) {
        return null;
    }

    @Override
    public void deleteMotorhomeModel(MotorhomeModelEntity entity) {

    }

    @Override
    public MotorhomeModelEntity saveMotorhomeModel(MotorhomeModelEntity entity) {
        return null;
    }

    @Override
    public MotorhomeModelEntity findOneMotorhomeModel(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeModelEntity findOneMotorhomeModel(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeEntity getMotorhomeById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<MotorhomeEntity> getAllMotorhome() throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeEntity createMotorhome(MotorhomeEntity entity) {
        return null;
    }

    @Override
    public void deleteMotorhome(MotorhomeEntity entity) {

    }

    @Override
    public MotorhomeEntity saveMotorhome(MotorhomeEntity entity) {
        return null;
    }

    @Override
    public MotorhomeEntity findOneMotorhome(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public MotorhomeEntity findOneMotorhome(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public RentalEntity getRentalById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<RentalEntity> getAlRentall() throws NoSuchEntityException {
        return null;
    }

    @Override
    public RentalEntity createRental(RentalEntity entity) {
        return null;
    }

    @Override
    public void deleteRental(RentalEntity entity) {

    }

    @Override
    public RentalEntity saveRental(RentalEntity entity) {
        return null;
    }

    @Override
    public RentalEntity findOneRental(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public RentalEntity findOneRental(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public RoleEntity getRoleById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<RoleEntity> getAllRole() throws NoSuchEntityException {
        return null;
    }

    @Override
    public RoleEntity createRole(RoleEntity entity) {
        return null;
    }

    @Override
    public void deleteRole(RoleEntity entity) {

    }

    @Override
    public RoleEntity saveRole(RoleEntity entity) {
        return null;
    }

    @Override
    public RoleEntity findOneRole(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public RoleEntity findOneRole(String key, int value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public StaffEntity getStaffById(int id) throws NoSuchEntityException {
        return null;
    }

    @Override
    public List<StaffEntity> getAllStaff() throws NoSuchEntityException {
        return null;
    }

    @Override
    public StaffEntity createStaff(StaffEntity entity) {
        return null;
    }

    @Override
    public void deleteStaff(StaffEntity entity) {

    }

    @Override
    public StaffEntity saveStaff(StaffEntity entity) {
        return null;
    }

    @Override
    public StaffEntity findOneStaff(String key, String value) throws NoSuchEntityException {
        return null;
    }

    @Override
    public StaffEntity findOneStaff(String key, int value) throws NoSuchEntityException {
        return null;
    }
}
