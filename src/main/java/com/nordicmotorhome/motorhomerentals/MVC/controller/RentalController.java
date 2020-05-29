package com.nordicmotorhome.motorhomerentals.MVC.controller;

import com.nordicmotorhome.motorhomerentals.MVC.FormObject.AddRentalFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.FormObject.CreatCustomerFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.FormObject.SearchFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.FormObject.SearchUserFormObject;
import com.nordicmotorhome.motorhomerentals.MVC.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import com.nordicmotorhome.motorhomerentals.domain.services.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping ("/rentals")
public class RentalController {
    CustomerService cs = new CustomerService();
    RentalService rs = new RentalService();

    @GetMapping("/customerselect")
    public String rentoptions(HttpServletRequest request, Model model){
        request.getSession().setAttribute("rental", new AddRentalFormObject());
        model.addAttribute("content","SelectRentOptionsView.html");
        return "index";
    }

    @GetMapping("/findcustomer")
    public String findCustomer(HttpServletRequest request, Model model){
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        model.addAttribute("CPRObject", new SearchUserFormObject());
        model.addAttribute("content","SelectCustomerView.html");
        return "index";
    }

    @PostMapping("/selectcustomer")
    public String selectCustomer(HttpServletRequest request, Model model) {
        AddRentalFormObject rental = (AddRentalFormObject) request.getSession().getAttribute("rental");

        rental.setCustomerID(Integer.parseInt(request.getParameter("id")));

        // Maybe not needed
        request.getSession().setAttribute("rental", rental);

        return "redirect:/rentals/searchmotorhome";
    }

    @PostMapping("/findcustomer")
    public String findCustomer(@ModelAttribute SearchUserFormObject cprObject, HttpServletRequest request,  Model model){
        model.addAttribute("customer", cs.findCustomer(cprObject.getCpr()));
        model.addAttribute("CPRObject", cprObject);
        model.addAttribute("content","SelectCustomerView.html");
        return "index";
    }

    @GetMapping("/createcustomer")
    public String createCustomer(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        model.addAttribute( "content","RegisterCustomerView.html" );
        model.addAttribute( "customerObject",new CreatCustomerFormObject() );
        return "index";
    }

    @PostMapping("/createcustomer")
    public String createCustomer(@ModelAttribute CreatCustomerFormObject customerObject, Model model) {
        cs.create( customerObject.getFirstName(), customerObject.getLastName(),customerObject.getNumber(),customerObject.getEmail(),customerObject.getCpr(),
                new StaffModel( null,null,null,null));
        model.addAttribute("content","RegisterCustomerView.html");
        model.addAttribute("customerObject",customerObject);
        return "index";
    }

    @GetMapping("/searchmotorhome")
    public String search(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        model.addAttribute("searchObject", new SearchFormObject());
        model.addAttribute("content","MotorhomeSearchView");
        return "index";
    }

    @PostMapping("/searchmotorhome")
    public String search(@ModelAttribute SearchFormObject searchObject, Model model) {
        model.addAttribute("results", rs.searchMotorhomes(searchObject.getBeds()));
        model.addAttribute("searchObject", searchObject);
        model.addAttribute("content", "MotorhomeSearchView.html");
        return "index";
    }

}
