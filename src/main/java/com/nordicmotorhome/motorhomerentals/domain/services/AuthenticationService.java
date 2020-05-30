package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.view.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.domain.entities.StaffEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.utils.HashUtil;

public class AuthenticationService {
    private final IDataFacade dataFacade = DataFacadeImpl.getInstance();
    private final IEntityModelMapper<StaffEntity, StaffModel> semm = new StaffEntityModelMapper();

    public StaffModel register(String email, String pw, String firstName, String lastName, int role_id) {
        try {
            StaffEntity se = new StaffEntity(0, firstName, lastName, dataFacade.getRoleById(role_id), email, null);
            pw = HashUtil.hash(pw);

            se.setPassword(pw);
            return semm.mapToModel(dataFacade.createStaff(se));
        } catch (NoSuchEntityException e) {
            return null;
        }
    }

    public StaffModel login(String email, String pw) {
        try {
            StaffEntity se = dataFacade.findOneStaff("email", email);

            if (HashUtil.verify(pw, se.getPassword())) return semm.mapToModel(se);
            else return null;
        } catch (NoSuchEntityException e) {
            return null;
        }

    }
}
