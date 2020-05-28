package com.nordicmotorhome.motorhomerentals.MVC.controller;

import com.nordicmotorhome.motorhomerentals.MVC.FormObject.CreatCustomerFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.FormObject.SearchUserFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
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
    @GetMapping("/createcustomer")
    public String createCustomer(Model model) {
        model.addAttribute( "content","registerCustomer.html" );
        model.addAttribute( "customerObject",new CreatCustomerFormObject() );
        return "index";
    }
    @PostMapping("/createcustomer")
    public String createCustomer(@ModelAttribute CreatCustomerFormObject customerObject, Model model) {
        cs.create( customerObject.getFirstName(), customerObject.getLastName(),customerObject.getNumber(),customerObject.getEmail(),customerObject.getCpr(),
                new StaffModel( null,null,null,null ) );
        model.addAttribute( "content","registerCustomer.html" );
        model.addAttribute( "customerObject",customerObject );
        return "index";
    }

}
