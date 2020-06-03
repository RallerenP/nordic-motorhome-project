package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.AddRentalFormObject;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.DomainFacadeImpl;
import com.nordicmotorhome.motorhomerentals.domain.IDomainFacade;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
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
//Author : RAP, AML, NKJ, ME
@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final IDomainFacade domainFacade = DomainFacadeImpl.getInstance();

    @GetMapping("/edit")
    public String updateCustomer(Model model) {
        model.addAttribute( "content", "UpdateCustomerView.html" );
        model.addAttribute( "customerObject", new CustomerFormObject() );
        return "index";
    }

    @PostMapping("/edit")
    public String findCustomer(@ModelAttribute CustomerFormObject customerObject, HttpServletRequest request, Model model){
        Message customerMessage = domainFacade.findCustomer(customerObject.getCpr());

        if (customerMessage.getType() == MessageType.ERROR) return "redirect:/";

        CustomerModel cm = (CustomerModel) customerMessage.getContent();
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
        Message submitMessage = domainFacade.updateCustomer(customerObject.getID(), customerObject.getFirstName(), customerObject.getLastName(), customerObject.getEmail(), customerObject.getPhone());

        if (submitMessage.getType() == MessageType.ERROR) return "redirect:/";
        return "redirect:/";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute CustomerFormObject customerObject, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        Message createMessage = domainFacade.createCustomer(customerObject.getFirstName(), customerObject.getLastName(),customerObject.getPhone(),customerObject.getEmail(),customerObject.getCpr(),
                new StaffModel( null,null,null,null)); // TODO Actual auth


        if (createMessage.getType() == MessageType.ERROR) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");
        arfo.setCustomerID(((CustomerModel)createMessage.getContent()).getID());

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/searchmotorhome";
    }






}
