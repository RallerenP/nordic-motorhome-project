package com.nordicmotorhome.motorhomerentals.MVC.controller;

import com.nordicmotorhome.motorhomerentals.MVC.FormObject.LoginFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {


        if (request.getSession().getAttribute("user") != null) {
            model.addAttribute("content", "HomeView");
        } else {
            model.addAttribute("content", "LoginView");
        }

        return "index";
    }


}
