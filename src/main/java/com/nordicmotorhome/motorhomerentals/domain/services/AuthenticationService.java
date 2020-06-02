package com.nordicmotorhome.motorhomerentals.domain.services;

import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.DataFacadeImpl;
import com.nordicmotorhome.motorhomerentals.data.IDataFacade;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.entities.StaffEntity;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.mappers.IEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.mappers.StaffEntityModelMapper;
import com.nordicmotorhome.motorhomerentals.domain.utils.HashUtil;

public class AuthenticationService {
    private final IDataFacade dataFacade = DataFacadeImpl.getInstance();
    private final IEntityModelMapper<StaffEntity, StaffModel> semm = new StaffEntityModelMapper();

    public Message register(String email, String pw, String firstName, String lastName, int role_id) {
        try {
            StaffEntity se = new StaffEntity(0, firstName, lastName, dataFacade.getRoleById(role_id), email, null);
            pw = HashUtil.hash(pw);

            se.setPassword(pw);

            StaffModel sm =semm.mapToModel(dataFacade.createStaff(se));
            return new Message(MessageType.SUCCESS, sm);
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.ERROR, "An unknown error occured");
        }
    }

    public Message login(String email, String pw) {
        try {
            StaffEntity se = dataFacade.findOneStaff("email", email);



            if (HashUtil.verify(pw, se.getPassword())) {
                StaffModel sm = semm.mapToModel(se);
                return new Message(MessageType.SUCCESS, sm);
            }
            else return new Message(MessageType.WARNING, "Wrong email/password");
        } catch (NoSuchEntityException e) {
            return new Message(MessageType.WARNING, "Wrong email/password");
        }

    }
}
