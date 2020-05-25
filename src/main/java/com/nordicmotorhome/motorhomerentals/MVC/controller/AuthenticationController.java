package com.nordicmotorhome.motorhomerentals.MVC.controller;

import com.nordicmotorhome.motorhomerentals.domain.services.AuthenticationService;
import com.nordicmotorhome.motorhomerentals.MVC.FormObject.LoginFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
