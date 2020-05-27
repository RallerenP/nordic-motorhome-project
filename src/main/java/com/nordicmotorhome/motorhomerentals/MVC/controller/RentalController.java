package com.nordicmotorhome.motorhomerentals.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/rentals")
public class RentalController {

    @GetMapping
    public String findCustomer(Model model){
        model.addAttribute("content","LoginUser.html");
        return "index";
    }
}
