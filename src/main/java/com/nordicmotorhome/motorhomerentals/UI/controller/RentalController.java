package com.nordicmotorhome.motorhomerentals.UI.controller;

import com.nordicmotorhome.motorhomerentals.data.Message;
import com.nordicmotorhome.motorhomerentals.domain.MessageType;
import com.nordicmotorhome.motorhomerentals.domain.services.AccessoryService;
import com.nordicmotorhome.motorhomerentals.domain.services.MotorhomeService;
import com.nordicmotorhome.motorhomerentals.UI.FormObject.AddRentalFormObject;
import com.nordicmotorhome.motorhomerentals.UI.FormObject.SearchFormObject;
import com.nordicmotorhome.motorhomerentals.UI.FormObject.CreateCustomerFormObject;

import com.nordicmotorhome.motorhomerentals.UI.FormObject.SearchUserFormObject;
import com.nordicmotorhome.motorhomerentals.UI.model.AccessoryModel;
import com.nordicmotorhome.motorhomerentals.UI.model.StaffModel;
import com.nordicmotorhome.motorhomerentals.UI.model.CustomerModel;
import com.nordicmotorhome.motorhomerentals.domain.services.CustomerService;
import com.nordicmotorhome.motorhomerentals.domain.services.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// AUTHORS: ME, AML, NKJ, RAP
@Controller
@RequestMapping ("/rentals")
public class RentalController {
    CustomerService cs = new CustomerService();
    RentalService rs = new RentalService();
    MotorhomeService ms = new MotorhomeService();
    AccessoryService as = new AccessoryService();

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
        model.addAttribute( "customerObject",new CreateCustomerFormObject() );
        return "index";
    }

    @PostMapping("/createcustomer")
    public String createCustomer(@ModelAttribute CreateCustomerFormObject customerObject, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";
        CustomerModel cm = cs.create( customerObject.getFirstName(), customerObject.getLastName(),customerObject.getNumber(),customerObject.getEmail(),customerObject.getCpr(),
                new StaffModel( null,null,null,null)); // TODO Actual auth

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");
        arfo.setCustomerID(cm.getID());

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/searchmotorhome";
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
        // TODO add parse exception handling
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate start = LocalDate.parse(searchObject.getStartDate(), dtf);
        LocalDate end = LocalDate.parse(searchObject.getEndDate(), dtf);

        model.addAttribute("results", ms.searchMotorhomes(searchObject.getBeds(), start, end));
        model.addAttribute("searchObject", searchObject);
        model.addAttribute("content", "MotorhomeSearchView.html");
        return "index";
    }

    @GetMapping("/addaccessories")
    public String addAccessories(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message billMessage = rs.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());

        if (billMessage.getType() == MessageType.ERROR) return "redirect:/rentals/searchmotorhome"; // TODO add user feedback

        model.addAttribute("billing", billMessage.getContent());

        model.addAttribute( "content", "RegisterAccessory.html" );
        model.addAttribute( "accessories", as.getAllAccessories());
        model.addAttribute("current_accessories", arfo.getAccessoriesMap());

        return "index";
    }

    @PostMapping("/addaccessory")
    public String addAccessory(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToAdd = as.getAccessory(id);

        arfoAccessoryMap.put(accessoryToAdd, arfoAccessoryMap.getOrDefault(accessoryToAdd, 0) + 1);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @PostMapping("/removeaccessory")
    public String removeAccessory(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        final Map<AccessoryModel, Integer> arfoAccessoryMap = arfo.getAccessoriesMap();
        final AccessoryModel accessoryToRemove = as.getAccessory(id);

        arfoAccessoryMap.put(accessoryToRemove, arfoAccessoryMap.getOrDefault(accessoryToRemove, 0) - 1);

        if (arfoAccessoryMap.get(accessoryToRemove) == 0) arfoAccessoryMap.remove(accessoryToRemove);

        // Maybe not needed
        request.getSession().setAttribute("rental", arfo);

        return "redirect:/rentals/addaccessories";

    }

    @GetMapping("/updatecustomer")
    public String updateCustomer(Model model) {
        model.addAttribute( "content", "UpdateCustomer.html" );
        model.addAttribute( "customerObject", new CreateCustomerFormObject() );
        return "index";
    }

    @GetMapping("/cancelrental")
    public String cancelRental(Model model){
        model.addAttribute("results", rs.findRentals());
        model.addAttribute("content", "CancelRental.html");
        return "index";
    }

    @PostMapping("/selectmotorhome")
    public String selectMotorhome(HttpServletRequest request) {
        AddRentalFormObject rental = (AddRentalFormObject) request.getSession().getAttribute("rental");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");

        rental.setMotorhomeID(Integer.parseInt(request.getParameter("id")));
        rental.setStartDate(LocalDate.parse(request.getParameter("startDate"), dtf));
        rental.setEndDate(LocalDate.parse(request.getParameter("endDate"), dtf));
        // Maybe not needed
        request.getSession().setAttribute("rental", rental);

        return "redirect:/rentals/addaccessories";
    }

    @GetMapping("/finish")
    public String getIntermediateBilling(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("rental") == null) return "redirect:/rentals/customerselect";

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");

        Message billMessage = rs.getBillingInfo(arfo.getAccessoriesMap(), arfo.getMotorhomeID(), arfo.getStartDate(), arfo.getEndDate());

        if (billMessage.getType() == MessageType.ERROR) {
            return "redirect:/rentals/searchmotorhome"; //Todo: add user feedback
        }

        model.addAttribute("billing", billMessage.getContent());
        model.addAttribute("content", "BillingPageView.html");

        return "index";
    }

    @GetMapping("/confirm")
    public String confirmOrder(HttpServletRequest request){

        AddRentalFormObject arfo = (AddRentalFormObject) request.getSession().getAttribute("rental");
        Message createMessage = rs.create(arfo.getCustomerID(), arfo.getStartDate(), arfo.getEndDate(), arfo.getMotorhomeID(),0,0);

        if (createMessage.getType() == MessageType.ERROR) return "redirect:/rentals/finish"; // todo add user feedback
        return "redirect:/";
    }
}
