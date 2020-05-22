package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.entity.StaffEntity;
import com.nordicmotorhome.motorhomerentals.data.repositories.RoleRepository;
import com.nordicmotorhome.motorhomerentals.data.repositories.StaffRepository;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.RoleEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.utils.HashUtil;

public class AuthenticationService {
    private StaffRepository sr = new StaffRepository();
    private RoleRepository rr = new RoleRepository();
    StaffEntityModelMapper semm = new StaffEntityModelMapper();

    public StaffModel register(String email, String pw, String firstName, String lastName, int role_id) {
        try {
            StaffEntity se = new StaffEntity(0, firstName, lastName, rr.getById(role_id), email, null);

            pw = HashUtil.hash(pw);

            se.setPassword(pw);
            return semm.mapToModel(sr.create(se));
        } catch (NoSuchEntityException e) {
            return null;
        }
    }

    public StaffModel login(String email, String pw) {
        try {
            StaffEntity se = sr.findOne("email", email);

            if (HashUtil.verify(pw, se.getPassword())) return semm.mapToModel(se);
            else return null;
        } catch (NoSuchEntityException e) {
            return null;
        }

    }
}
