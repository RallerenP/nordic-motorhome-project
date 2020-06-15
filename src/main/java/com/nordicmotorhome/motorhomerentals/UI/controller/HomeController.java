package com.nordicmotorhome.motorhomerentals.UI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;

// AUTHORS: RAP
//Used for loading homepage
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("content", "HomeView");

        return "index";
    }


}
