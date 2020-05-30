package com.nordicmotorhome.motorhomerentals.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("content", "HomeView");

        return "index";
    }


}
