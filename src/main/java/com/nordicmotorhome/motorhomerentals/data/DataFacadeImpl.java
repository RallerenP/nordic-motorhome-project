package com.nordicmotorhome.motorhomerentals.data;

import com.nordicmotorhome.motorhomerentals.data.repositories.*;
import com.nordicmotorhome.motorhomerentals.domain.entities.*;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;

import java.util.List;

public class DataFacadeImpl implements IDataFacade {
    private static final DataFacadeImpl instance = new DataFacadeImpl();
    public static DataFacadeImpl getInstance() { return instance; }

    private final IRepository<AccessoryEntity> ar = new AccessoryRepository();
    private final IRepository<AccessoryStockEntity> asr = new AccessoryStockRepository();
    private final IRepository<CustomerEntity> cr = new CustomerRepository();
    private final IRepository<MotorhomeEntity> mr = new MotorhomeRepository();
    private final IRepository<MotorhomeModelEntity> mmr = new MotorhomeModelRepository();
    private final IRepository<RentalEntity> rtr = new RentalRepository();
    private final IRepository<RoleEntity> rr = new RoleRepository();
    private final IRepository<StaffEntity> sr = new StaffRepository();

    @Override
    public AccessoryEntity getAccessoryById(int id) throws NoSuchEntityException {
        return ar.getById(id);
    }

    @Override
    public List<AccessoryEntity> getAllAccessories() throws NoSuchEntityException {
        return ar.getAll();
    }

    @Override
    public AccessoryEntity createAccessory(AccessoryEntity entity) {
        return ar.create(entity);
    }

    @Override
    public void deleteAccessory(AccessoryEntity entity) {
        ar.delete(entity);
    }

    @Override
    public AccessoryEntity saveAccessory(AccessoryEntity entity) {
        return ar.save(entity);
    }

    @Override
    public AccessoryEntity findOneAccessory(String key, String value) throws NoSuchEntityException {
        return ar.findOne(key, value);
    }

    @Override
    public AccessoryEntity findOneAccessory(String key, int value) throws NoSuchEntityException {
        return ar.findOne(key, value);
    }

    @Override
    public AccessoryStockEntity getAccessoryStockById(int id) throws NoSuchEntityException {
        return asr.getById(id);
    }

    @Override
    public List<AccessoryStockEntity> getAllAccessoryStock() throws NoSuchEntityException {
        return asr.getAll();
    }

    @Override
    public AccessoryStockEntity createAccessoryStock(AccessoryStockEntity entity) {
        return asr.save(entity);
    }

    @Override
    public void deleteAccessoryStock(AccessoryStockEntity entity) {
        asr.delete(entity);
    }

    @Override
    public AccessoryStockEntity saveAccessoryStock(AccessoryStockEntity entity) {
        return asr.save(entity);
    }

    @Override
    public AccessoryStockEntity findOneAccessoryStock(String key, String value) throws NoSuchEntityException {
        return asr.findOne(key, value);
    }

    @Override
    public AccessoryStockEntity findOneAccessoryStock(String key, int value) throws NoSuchEntityException {
        return asr.findOne(key, value);
    }

    @Override
    public CustomerEntity getCustomerById(int id) throws NoSuchEntityException {
        return cr.getById(id);
    }

    @Override
    public List<CustomerEntity> getAllCustomers() throws NoSuchEntityException {
        return cr.getAll();
    }

    @Override
    public CustomerEntity createCustomer(CustomerEntity entity) {
        return cr.create(entity);
    }

    @Override
    public void deleteCustomer(CustomerEntity entity) {
        cr.delete(entity);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity entity) {
        return cr.save(entity);
    }

    @Override
    public CustomerEntity findOneCustomer(String key, String value) throws NoSuchEntityException {
        return cr.findOne(key, value);
    }

    @Override
    public CustomerEntity findOneCustomer(String key, int value) throws NoSuchEntityException {
        return cr.findOne(key, value);
    }

    @Override
    public MotorhomeModelEntity getMotorhomeModelById(int id) throws NoSuchEntityException {
        return mmr.getById(id);
    }

    @Override
    public List<MotorhomeModelEntity> getAllMotorhomeModels() throws NoSuchEntityException {
        return mmr.getAll();
    }

    @Override
    public MotorhomeModelEntity createMotorhomeModel(MotorhomeModelEntity entity) {
        return mmr.create(entity);
    }

    @Override
    public void deleteMotorhomeModel(MotorhomeModelEntity entity) {
        mmr.delete(entity);
    }

    @Override
    public MotorhomeModelEntity saveMotorhomeModel(MotorhomeModelEntity entity) {
        return mmr.save(entity);
    }

    @Override
    public MotorhomeModelEntity findOneMotorhomeModel(String key, String value) throws NoSuchEntityException {
        return mmr.findOne(key, value);
    }

    @Override
    public MotorhomeModelEntity findOneMotorhomeModel(String key, int value) throws NoSuchEntityException {
        return mmr.findOne(key, value);
    }

    @Override
    public MotorhomeEntity getMotorhomeById(int id) throws NoSuchEntityException {
        return mr.getById(id);
    }

    @Override
    public List<MotorhomeEntity> getAllMotorhomes() throws NoSuchEntityException {
        return mr.getAll();
    }

    @Override
    public MotorhomeEntity createMotorhome(MotorhomeEntity entity) {
        return mr.create(entity);
    }

    @Override
    public void deleteMotorhome(MotorhomeEntity entity) {
        mr.delete(entity);
    }

    @Override
    public MotorhomeEntity saveMotorhome(MotorhomeEntity entity) {
        return mr.save(entity);
    }

    @Override
    public MotorhomeEntity findOneMotorhome(String key, String value) throws NoSuchEntityException {
        return mr.findOne(key, value);
    }

    @Override
    public MotorhomeEntity findOneMotorhome(String key, int value) throws NoSuchEntityException {
        return mr.findOne(key, value);
    }

    @Override
    public RentalEntity getRentalById(int id) throws NoSuchEntityException {
        return rtr.getById(id);
    }

    @Override
    public List<RentalEntity> getAllRentals() throws NoSuchEntityException {
        return rtr.getAll();
    }

    @Override
    public RentalEntity createRental(RentalEntity entity) {
        return rtr.create(entity);
    }

    @Override
    public void deleteRental(RentalEntity entity) {
        rtr.delete(entity);
    }

    @Override
    public RentalEntity saveRental(RentalEntity entity) {
        return rtr.save(entity);
    }

    @Override
    public RentalEntity findOneRental(String key, String value) throws NoSuchEntityException {
        return rtr.findOne(key, value);
    }

    @Override
    public RentalEntity findOneRental(String key, int value) throws NoSuchEntityException {
        return rtr.findOne(key,value);
    }

    @Override
    public RoleEntity getRoleById(int id) throws NoSuchEntityException {
        return rr.getById(id);
    }

    @Override
    public List<RoleEntity> getAllRoles() throws NoSuchEntityException {
        return rr.getAll();
    }

    @Override
    public RoleEntity createRole(RoleEntity entity) {
        return rr.create(entity);
    }

    @Override
    public void deleteRole(RoleEntity entity) {
        rr.delete(entity);
    }

    @Override
    public RoleEntity saveRole(RoleEntity entity) {
        return rr.save(entity);
    }

    @Override
    public RoleEntity findOneRole(String key, String value) throws NoSuchEntityException {
        return rr.findOne(key,value);
    }

    @Override
    public RoleEntity findOneRole(String key, int value) throws NoSuchEntityException {
        return rr.findOne(key,value);
    }

    @Override
    public StaffEntity getStaffById(int id) throws NoSuchEntityException {
        return sr.getById(id);
    }

    @Override
    public List<StaffEntity> getAllStaff() throws NoSuchEntityException {
        return sr.getAll();
    }

    @Override
    public StaffEntity createStaff(StaffEntity entity) {
        return sr.create(entity);
    }

    @Override
    public void deleteStaff(StaffEntity entity) {
        sr.delete(entity);
    }

    @Override
    public StaffEntity saveStaff(StaffEntity entity) {
        return sr.save(entity);
    }

    @Override
    public StaffEntity findOneStaff(String key, String value) throws NoSuchEntityException {
        return sr.findOne(key,value);
    }

    @Override
    public StaffEntity findOneStaff(String key, int value) throws NoSuchEntityException {
        return sr.findOne(key,value);
    }
}
