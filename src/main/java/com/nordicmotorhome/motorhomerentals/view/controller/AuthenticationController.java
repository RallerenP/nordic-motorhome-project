package com.nordicmotorhome.motorhomerentals.view.controller;

import com.nordicmotorhome.motorhomerentals.domain.services.AuthenticationService;
import com.nordicmotorhome.motorhomerentals.view.FormObject.LoginFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// AUTHORS: RAP
@Controller()
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService as = new AuthenticationService();

    @PostMapping("/login")
    public String login(@ModelAttribute LoginFormObject loginFormObject, HttpServletRequest request) {
        request.getSession().setAttribute("user", as.login(loginFormObject.getEmail(), loginFormObject.getPassword()));
        return "redirect:/";
    }

}
