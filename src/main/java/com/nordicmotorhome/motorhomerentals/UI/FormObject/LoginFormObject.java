package com.nordicmotorhome.motorhomerentals.UI.FormObject;

//Author : RAP
//Form used in views for creating login thymeleaf object
public class LoginFormObject {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
