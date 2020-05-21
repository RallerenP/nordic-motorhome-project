package com.nordicmotorhome.motorhomerentals.domain.exceptions.services;

import com.nordicmotorhome.motorhomerentals.data.entity.Role;
import com.nordicmotorhome.motorhomerentals.data.entity.Staff;
import com.nordicmotorhome.motorhomerentals.data.mappers.RoleMapper;
import com.nordicmotorhome.motorhomerentals.data.mappers.StaffMapper;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.NoSuchEntityException;
import com.nordicmotorhome.motorhomerentals.domain.exceptions.utils.BCryptUtil;

public class AuthenticationService {
    private StaffMapper sm = new StaffMapper();
    private RoleMapper rm = new RoleMapper();

    public Staff register(String email, String pw, String firstName,  String lastName, int role_id) {
        try {
            Staff staff = new Staff(0, firstName, lastName, rm.get(role_id), email);

            pw = BCryptUtil.hash(pw);
            return sm.insert(staff, pw);
        } catch (NoSuchEntityException e) {
            return null;
        }

    }

    public Staff login(String email, String pw) {
        try {
            Staff staff = sm.getByEmail(email);
            String hashedPw = sm.getPwById(staff.getID());

            if (BCryptUtil.verify(pw, hashedPw)) return staff;
            else return null;

        } catch (NoSuchEntityException e) {
            return null;
        }

    }
}
