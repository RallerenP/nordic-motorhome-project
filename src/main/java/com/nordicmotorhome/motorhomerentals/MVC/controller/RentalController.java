package com.nordicmotorhome.motorhomerentals.MVC.controller;

import com.nordicmotorhome.motorhomerentals.MVC.FormObject.SearchUserFormObject;
import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/rentals")
public class RentalController {
    CustomerService cs = new CustomerService();

    @GetMapping("/finduser")
    public String findCustomer(Model model){
        model.addAttribute("CPRObject", new SearchUserFormObject());
        model.addAttribute("content","LoginUser.html");
        return "index";
    }
    @PostMapping("/finduser")
    public String findCustomer(@ModelAttribute SearchUserFormObject cprObject, Model model){
        model.addAttribute("customer", cs.findCustomer(cprObject.getCpr()));
        model.addAttribute("CPRObject", cprObject);
        model.addAttribute("content","LoginUser.html");
        return "index";
    }
}
