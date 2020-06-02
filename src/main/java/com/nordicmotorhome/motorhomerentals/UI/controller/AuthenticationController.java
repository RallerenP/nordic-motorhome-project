package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.DomainFacadeImpl;
import com.nordicmotorhome.motorhomerentals.domain.IDomainFacade;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.services.AuthenticationService;
import com.nordicmotorhome.motorhomerentals.UI.FormObject.LoginFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// AUTHORS: RAP
@Controller()
@RequestMapping("/auth")
public class AuthenticationController {
    private final IDomainFacade domainFacade = DomainFacadeImpl.getInstance();

    @PostMapping("/login")
    public String login(@ModelAttribute LoginFormObject loginFormObject, HttpServletRequest request) {
        Message loginMessage = domainFacade.loginStaff(loginFormObject.getEmail(), loginFormObject.getPassword());

        if (loginMessage.getType() == MessageType.ERROR) return "redirect:/"; // Todo: something else

        return "redirect:/";
    }

}
