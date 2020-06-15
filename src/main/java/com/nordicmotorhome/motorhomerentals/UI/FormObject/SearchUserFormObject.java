package com.nordicmotorhome.motorhomerentals.UI.FormObject;

//Author : RAP, AML, NKJ, ME
//Form used in views for creating customer search thymeleaf object
public class SearchUserFormObject {
    String cpr;

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getCpr() {
        return cpr;
    }
}
