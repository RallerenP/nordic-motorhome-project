package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import com.nordicmotorhome.motorhomerentals.UI.FormObject.CustomerFormObject;
import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    CustomerService cs = new CustomerService();

    @GetMapping("/edit")
    public String updateCustomer(Model model) {
        model.addAttribute( "content", "UpdateCustomerView.html" );
        model.addAttribute( "customerObject", new CustomerFormObject() );
        return "index";
    }

    @PostMapping("/edit")
    public String findCustomer(@ModelAttribute CustomerFormObject customerObject, HttpServletRequest request, Model model){
        CustomerModel cm = cs.findCustomer(customerObject.getCpr());
        customerObject.setEmail(cm.getEmail());
        customerObject.setFirstName(cm.getFirstName());
        customerObject.setLastName(cm.getLastName());
        customerObject.setPhone(Integer.parseInt(cm.getPhone()));
        customerObject.setID(cm.getID());

        model.addAttribute("customerObject", customerObject);
        model.addAttribute("content","UpdateCustomerView.html");
        return "index";
    }

    @PostMapping("/editSubmit")
    public String submitCustomer(@ModelAttribute CustomerFormObject customerObject) {
        cs.update(customerObject.getID(), customerObject.getFirstName(), customerObject.getLastName(), customerObject.getEmail(), customerObject.getPhone());
        return "redirect:/";
    }







}
