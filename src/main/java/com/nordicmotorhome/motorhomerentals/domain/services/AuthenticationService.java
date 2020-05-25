package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.entity.RoleEntity;
import com.nordicmotorhome.motorhomerentals.data.entity.StaffEntity;
import com.nordicmotorhome.motorhomerentals.data.repositories.IRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.RoleRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.StaffRepository;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.utils.HashUtil;

public class AuthenticationService {
    private final IRepository<StaffEntity> staffRepository = new StaffRepository();
    private final IRepository<RoleEntity> roleRepository = new RoleRepository();

    private final IEntityModelMapper<StaffEntity, StaffModel> semm = new StaffEntityModelMapper();

    public StaffModel register(String email, String pw, String firstName, String lastName, int role_id) {
        try {
            StaffEntity se = new StaffEntity(0, firstName, lastName, roleRepository.getById(role_id), email, null);
            pw = HashUtil.hash(pw);

            se.setPassword(pw);
            return semm.mapToModel(staffRepository.create(se));
        } catch (NoSuchEntityException e) {
            return null;
        }
    }

    public StaffModel login(String email, String pw) {
        try {
            StaffEntity se = staffRepository.findOne("email", email);

            if (HashUtil.verify(pw, se.getPassword())) return semm.mapToModel(se);
            else return null;
        } catch (NoSuchEntityException e) {
            return null;
        }

    }
}
